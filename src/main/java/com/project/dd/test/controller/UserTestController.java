package com.project.dd.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserTestController {

	@GetMapping(value = "/user/test/view.do")
	public String view(Model model) {

		return "user/test/view";
	}
	
}
