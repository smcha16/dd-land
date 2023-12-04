package com.project.dd.mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/layout")
@Controller
public class UserController {

	@GetMapping(value = "/mypage.do")
	public String view(Model model) {

		return "mypage";
	}
}
