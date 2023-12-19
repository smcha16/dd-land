package com.project.dd.shop.giftshop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.shop.giftshop.domain.GiftshopImageDTO;
import com.project.dd.shop.giftshop.domain.ShopDTO;
import com.project.dd.shop.giftshop.service.GiftshopService;

@Controller
public class AdminGiftshopController {

	@Autowired
	private GiftshopService service;

	@GetMapping(value = "/admin/shop/gift-shop/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, String word, Model model) {

		String searchStatus = (word == null || word.equals("")) ? "n" : "y";
		
		String solting = "admin";
		Map<String, String> map = service.paging(page, word, searchStatus, solting);

		List<ShopDTO> list = service.getList(map);

		List<GiftshopImageDTO> ilist = service.getImgList();

		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);

		model.addAttribute("list", list);

		model.addAttribute("ilist", ilist);

		return "admin/shop/gift-shop/view";
	}
	
	@PostMapping(value = "/admin/shop/gift-shop/del.do")
	public String del(Model model, String[] shop_seq) {
		
		int result = service.delGiftshop(shop_seq);
		
		if (result > 0) {
			return "redirect:/admin/shop/gift-shop/view.do";
		} else {
			return "redirect:/admin/shop/gift-shop/view.do";
		}
	}

}
