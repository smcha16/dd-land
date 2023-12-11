package com.project.dd.activity.attraction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.activity.attraction.service.AttractionService;

@Controller
@RequestMapping(value = "/member/activity/attraction")
public class MemberAttractionController {

	@Autowired
	private AttractionService servcie;
	
	@GetMapping(value = "/add.do")
	public String add(Model model) {

		return "add";
	}
	
}
