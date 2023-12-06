package com.project.dd.shop.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.shop.restaurant.mapper.RestaurantMapper;

@Controller
@RequestMapping("/user/shop/restaurant")
public class UserRestaurantController {

	@Autowired
	private RestaurantMapper mapper;
	
	@GetMapping(value = "/view.do")
	public String list(Model model) {
		
		model.addAttribute("list", mapper.list());

		return "user/shop/restaurant/view";
	}
	
	@GetMapping(value = "/detail.do")
	public String detail(Model model, String seq) {

		model.addAttribute("dto", mapper.detail(seq));
		model.addAttribute("list", mapper.image(seq));
		
		return "user/shop/restaurant/detail";
	}
	
}
