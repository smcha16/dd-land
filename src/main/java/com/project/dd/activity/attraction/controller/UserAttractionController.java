package com.project.dd.activity.attraction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.activity.attraction.mapper.AttractionMapper;

@Controller
@RequestMapping(value = "/user/activity/attraction")
public class UserAttractionController {

	@Autowired
	private AttractionMapper mapper;
	
	@GetMapping(value = "/view.do")
	public String view(Model model, String close) {
		
		//운영: close=n, 운휴: close=y
		if (close == null || close.equals("")) {
			close = "n";
		} 

		model.addAttribute("list", mapper.list(close));
		model.addAttribute("close", close);

		return "user/activity/attraction/view";
	}

	@GetMapping(value = "/detail.do")
	public String detail(Model model, String seq) {
		
		//1. 어트 2. 어트 이미지 3. 어트 운휴 4. 어트 위치
		//tblAttraction, tblAttractionImg, tblAttractionClose, tblAttractionLocation
		
		//AttractionDTO
		//AttractionImgDTO
		//AttractionCloseDTO
		
//		model.addAttribute("dto", mapper.get(seq));

		
		return "user/activity/attraction/detail";
	}
}
