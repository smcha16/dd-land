package com.project.dd.test.mbti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserMBTIController {

	@GetMapping(value = "/user/test/mbti/view.do")
	public String view(Model model) {

		return "user/test/mbti/view";
	}
	
}
