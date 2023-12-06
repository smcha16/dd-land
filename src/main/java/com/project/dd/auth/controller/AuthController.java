package com.project.dd.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

	@GetMapping(value = "/auth/accesserror.do")
	public String auth(Model model) {
		
		return "auth/accesserror";
	}
	
	/*
	@GetMapping(value = "/auth/mylogout.do")
	public String mylogout(Model model) {

		return "auth/mylogout";
	}

	@GetMapping(value = "/auth/myinfo.do")
	public String myinfo(Model model) {

		return "auth/myinfo";
	}
	*/
	
}