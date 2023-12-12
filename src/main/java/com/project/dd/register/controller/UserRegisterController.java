package com.project.dd.register.controller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.register.service.RegisterService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/register")
public class UserRegisterController {

	private final RegisterService service;
	
	@GetMapping("/view.do")
	public String view() {
		
		
		return"user/register/view";
	}
	
	@PostMapping("/view.do")
	public String register() {
		
		
		
		return "/dd/main.do";
	}
	
	
	@PostMapping("/duplicatecheck.do")
	 public void duplicateCheck(String email,HttpServletResponse resp) throws IOException {
		
		int message = service.check(email);
		
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = resp.getWriter();
		
		writer.printf("{ \"message\": %d }", message);
		writer.close();
	}
}
