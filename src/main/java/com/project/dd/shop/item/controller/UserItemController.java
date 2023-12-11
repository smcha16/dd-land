package com.project.dd.shop.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.shop.item.service.ItemService;

@Controller
public class UserItemController {
	
	@Autowired
	private ItemService service;
	
	@GetMapping(value = "/user/shop/gift-shop/item/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {

		return "user/shop/gift-shop/item/view";
	}

}
