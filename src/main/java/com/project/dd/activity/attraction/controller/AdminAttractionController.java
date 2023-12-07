package com.project.dd.activity.attraction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.activity.attraction.mapper.AttractionMapper;

@Controller
@RequestMapping(value = "/admin/activity/attraction")
public class AdminAttractionController {
	
	@Autowired
	private AttractionMapper mapper;
	
	@GetMapping(value = "/activity/attraction/add.do")
	public String add(Model model) {

		return "admin/activity/attraction/add";
		
	}

	@PostMapping(value = "/activity/attraction/add.do")
	public String addok(Model model) {
		
		//0. 어트랙션 위치 중복 검사
		//1. 어트랙션 추가
		//2. 어트랙션 위치 추가
		//3. 어트랙션 이미지 추가
		
		
		return "admin/activity/attraction/add";
		
	}

}
