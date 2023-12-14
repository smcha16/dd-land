	package com.project.dd.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.dd.login.mapper.LoginMapper;

@Controller
public class UserLoginController {
	
	/*
	 * @Autowired private PasswordEncoder encoder;
	 */
	
	@Autowired
	private LoginMapper mapper;
	
	
	@GetMapping(value = "/user/login/view.do")
	public String login(Model model) {

		return "user/login/view";
	}
	
}
