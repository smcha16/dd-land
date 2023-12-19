package com.project.dd.purchase.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.dd.cart.service.CartService;
import com.project.dd.purchase.domain.PurchaseDTO;
import com.project.dd.purchase.service.PurchaseService;
import com.project.dd.shop.item.domain.ItemDTO;

@Controller
public class MemberPurchaseController {

	@Autowired
	private PurchaseService service;
	
	@Autowired CartService cartService;

	@PostMapping(value = "/member/purchase/view.do")
	public String view(Model model, String selectedItem, ItemDTO dto, String[] cart_seq) {

		int totalPrice = 0;
		
		if (cart_seq != null) {
			List<ItemDTO> list = new ArrayList<ItemDTO> ();
			
			for (String seq : cart_seq) {
				ItemDTO idto = service.getCartList(seq);
				
				totalPrice += Integer.parseInt(idto.getEa()) * Integer.parseInt(idto.getPrice());
				
				list.add(idto);
			}
			
			model.addAttribute("list", list);
		}

		if (selectedItem != null) {
			totalPrice += Integer.parseInt(dto.getEa()) * Integer.parseInt(dto.getPrice());

			model.addAttribute("dto", dto);
		}

		model.addAttribute("totalPrice", totalPrice);

		return "member/purchase/view";
	}

	@PostMapping(value = "/member/purchase/ok.do")
	public String ok(Model model, String user_seq, String[] item_seq, String[] ea, String[] price, String[] cart_seq) {

		for (int i = 0; i < item_seq.length; i++) {
			PurchaseDTO dto = new PurchaseDTO();
			
			dto.setItem_seq(item_seq[i]);
			dto.setEa(ea[i]);
			dto.setPrice(price[i]);
			
			int result = service.addBuy(dto);

			if (result == 1) {
				String seq = service.getSeq();

				Map<String, String> map = new HashMap<String, String>();

				map.put("seq", seq);
				map.put("user_seq", user_seq);

				result = service.addUserBuy(map);
			}
		}
		
		if (cart_seq != null) {
			int result = cartService.delCart(cart_seq);
		}

		return "redirect:/index.do";
	}

}
