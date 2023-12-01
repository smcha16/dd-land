package com.project.dd.activity.attraction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	
	@GetMapping(value = "/attraction.do")
	public String attraction(Model model) {

		return "attraction";
	}

}
