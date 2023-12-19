package com.project.dd.shop.giftshop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.shop.giftshop.domain.ShopDTO;
import com.project.dd.shop.giftshop.service.GiftshopService;

@Controller
public class UserGiftshopController {

	@Autowired
	private GiftshopService service;
	
	@GetMapping(value = "/user/shop/gift-shop/view.do")
	public String userview(@RequestParam(defaultValue = "1") int page, Model model) {
		
		Map<String, String> map = service.paging(page);  //페이징
		List<ShopDTO> list = service.getList(map);
		
		int closeCount = service.getShopCloseCount(list);
		
		model.addAttribute("list", list);
		model.addAttribute("currentPage", page);  //페이징
	    model.addAttribute("map", map);  //페이징
	    model.addAttribute("closeCount", closeCount);

		return "user/shop/gift-shop/view";
	}
	
	@GetMapping(value = "/user/shop/gift-shop/detail.do")
	public String detail(Model model, String seq) {
		
		model.addAttribute("dto", service.detail(seq));
		model.addAttribute("list", service.getImg(seq));

		return "user/shop/gift-shop/detail";
	}
	
}
