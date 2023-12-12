package com.project.dd.shop.item.controller;

import java.util.Map;

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
	public String view(@RequestParam(defaultValue = "1") int page, String seq, Model model) {

		Map<String, String> map = service.paging(page, seq);
		
		map.put("seq", seq);
		
		model.addAttribute("list", service.getList(map));
		model.addAttribute("currentPage", page);  //페이징
	    model.addAttribute("map", map);  //페이징
	    model.addAttribute("seq", seq);
		
		return "user/shop/gift-shop/item/view";
	}
	
	@GetMapping(value = "/user/shop/gift-shop/item/detail.do")
	public String detail(Model model, String seq) {
		
		model.addAttribute("dto", service.getItem(seq));

		return "user/shop/gift-shop/item/detail";
	}

}
