package com.project.dd.communication.faq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.dd.communication.faq.mapper.FaqMapper;

@Controller
public class FaqUserController {
	
	@Autowired
	private FaqMapper mapper;
	
	@GetMapping(value = "/user/communication/faq/view.do")
	public String view(Model model) {

		return "user/communication/faq/view";

	}

}
