package com.project.dd.shop.restaurant.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.shop.restaurant.domain.RestaurantDTO;
import com.project.dd.shop.restaurant.service.RestaurantService;

@Controller
@RequestMapping("/user/shop/restaurant")
public class UserRestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
	@GetMapping(value = "/view.do")
	public String list(@RequestParam(defaultValue = "1") int page, Model model) {
		
		Map<String, String> map = restaurantService.paging(page);  //페이징
		
		List<RestaurantDTO> list = restaurantService.getList(map);
		
		int closeCount = restaurantService.getRestaurantCloseCount(list);
		
		model.addAttribute("list", list);
		model.addAttribute("currentPage", page);  //페이징
	    model.addAttribute("map", map);  //페이징
	    model.addAttribute("closeCount", closeCount);

		return "user/shop/restaurant/view";
	}
	
	@GetMapping(value = "/detail.do")
	public String detail(Model model, String seq) {

		model.addAttribute("dto", restaurantService.detail(seq));
		model.addAttribute("list", restaurantService.getImg(seq));
		
		return "user/shop/restaurant/detail";
	}
	
}
