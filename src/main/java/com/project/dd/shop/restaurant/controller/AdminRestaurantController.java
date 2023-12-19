package com.project.dd.shop.restaurant.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.shop.restaurant.domain.RestaurantDTO;
import com.project.dd.shop.restaurant.domain.RestaurantImageDTO;
import com.project.dd.shop.restaurant.service.RestaurantService;

@Controller
public class AdminRestaurantController {

	@Autowired
	private RestaurantService service;
	
	@GetMapping(value = "/admin/shop/restaurant/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {
		
		String solting = "admin";
		Map<String, String> map = service.paging(page, solting);
		
		List<RestaurantDTO> list = service.getList(map);
		
		List<RestaurantImageDTO> ilist = service.getImgList();
		
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		
		model.addAttribute("list", list);
		
		model.addAttribute("ilist", ilist);

		return "admin/shop/restaurant/view";
	}
	
}
