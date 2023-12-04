package com.project.dd.activity.attraction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.dd.activity.attraction.mapper.AttractionMapper;

@Controller
public class UserAttractionController {

	@Autowired
	private AttractionMapper mapper;
	
	@GetMapping(value = "/user/activity/attraction/view.do")
	public String view(Model model) {
		
		model.addAttribute("list", mapper.list());

		return "user/activity/attraction/view";
	}

	@GetMapping(value = "/user/activity/attraction/detail.do")
	public String detail(Model model, String seq) {
		
		//1. 어트 2. 어트 이미지 3. 어트 운휴 4. 어트 위치
		//tblAttraction, tblAttractionImg, tblAttractionClose, tblAttractionLocation
		
		//AttractionDTO
		//AttractionImgDTO
		//AttractionCloseDTO

		
		return "user/activity/attraction/detail";
	}
}
