package com.project.dd.test.worldcup.attraction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserWorldCupAttractionController {

	@GetMapping(value = "/user/test/worldcup/attraction/view.do")
	public String view(Model model) {

		return "user/test/worldcup/attraction/view";
	}
	
}
