package com.project.dd.purchase.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.dd.purchase.domain.PurchaseDTO;
import com.project.dd.purchase.service.PurchaseService;
import com.project.dd.shop.item.domain.ItemDTO;

@Controller
public class MemberPurchaseController {

	@Autowired
	private PurchaseService service;
	
	@PostMapping(value = "/member/purchase/view.do")
	public String view(Model model, String selectedItem, ItemDTO dto) {
		
		int totalPrice = 0;
		
		if (selectedItem != null) {
			
		} else {
			totalPrice += Integer.parseInt(dto.getEa()) * Integer.parseInt(dto.getPrice());
			
			model.addAttribute("dto", dto);
		}
		
		model.addAttribute("totalPrice", totalPrice);

		return "member/purchase/view";
	}
	
	@PostMapping(value = "/member/purchase/ok.do")
	public String ok(Model model, String user_seq, PurchaseDTO dto) {
		
		int result = service.addBuy(dto);
		
		if (result == 1) {
			String seq = service.getSeq();
			
			Map<String, String> map = new HashMap<String, String>();
			
			map.put("seq", seq);
			map.put("user_seq", user_seq);
			
			result = service.addUserBuy(map);
		}

		return "redirect:/index.do";
	}
	
}
