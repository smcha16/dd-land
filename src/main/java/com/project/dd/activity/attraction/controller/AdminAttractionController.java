package com.project.dd.activity.attraction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminAttractionController {
	
	@GetMapping(value = "/activity/attraction/view.do")
	public String attractionView(Model model) {

		return "admin/activity/attraction/view";
		
	}

}