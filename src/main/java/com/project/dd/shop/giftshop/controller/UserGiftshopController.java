package com.project.dd.shop.giftshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.dd.shop.giftshop.service.GiftshopService;

@Controller
public class UserGiftshopController {

	@Autowired
	private GiftshopService service;
	
	@GetMapping(value = "/user/shop/gift-shop/view.do")
	public String userview(Model model) {
		
		model.addAttribute("list", service.getList());

		return "user/shop/gift-shop/view";
	}
	
}