package com.project.dd.activity.attraction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserAttractionController {

	@GetMapping(value = "/user/activity/attraction/view.do")
	public String view(Model model) {

		return "user/activity/attraction/view";
	}

	@GetMapping(value = "/user/activity/attraction/detail.do")
	public String detail(Model model) {
		
		return "user/activity/attraction/detail";
	}
}
