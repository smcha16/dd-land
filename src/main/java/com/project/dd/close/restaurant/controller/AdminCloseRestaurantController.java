package com.project.dd.close.restaurant.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.close.restaurant.domain.CloseRestaurantDTO;
import com.project.dd.close.restaurant.service.CloseRestaurantService;

@Controller
@RequestMapping(value = "/admin/close/restaurant")
public class AdminCloseRestaurantController {
	
	@Autowired
	private CloseRestaurantService service;
	
	@GetMapping(value = "/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {
		
		Map<String, String> map = service.paging(page);  //페이징
		
		List<CloseRestaurantDTO> list = service.list(map); 
		
		model.addAttribute("currentPage", page);  //페이징
		model.addAttribute("map", map);  //페이징
		model.addAttribute("list", list);
		
		return "admin/close/restaurant/view";
	}
	
	//추가하기
	@GetMapping(value = "/add.do")
	public String add(Model model) {
				
		model.addAttribute("restaurantlist", service.restaurantlist());  

		return "admin/close/restaurant/add";
	}
			
	@PostMapping(value = "/addok.do")
	public String addok(CloseRestaurantDTO dto) {
		
		int result = service.addCloseRestaurant(dto);
				
		if (result > 0) {
			return "redirect:/admin/close/restaurant/view.do";
		} else {  //실패
			return "redirect:/admin/close/restaurant/add.do";
		}
				
	}

	//수정하기
	@GetMapping(value = "/edit.do")
	public String edit(Model model, String seq) {
				
		CloseRestaurantDTO dto = service.getOne(seq); 
				
		model.addAttribute("dto", dto);
				
		return "admin/close/restaurant/edit";
	}
			
	@PostMapping(value = "/editok.do")
	public String editok(CloseRestaurantDTO dto) {

		int result = service.editClose(dto);
						
		if (result == 1) {
		 	return "redirect:/admin/close/restaurant/view.do";
		} else 
		 	return "redirect:/admin/close/restaurant/edit.do";
	}
			
	//삭제하기
	@PostMapping(value = "/del.do")
	public String del(Model model, String[] closeRestaurant_seq) {

		service.del(closeRestaurant_seq);
				
		return "redirect:/admin/close/restaurant/view.do";
	}
	
}
