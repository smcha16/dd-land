package com.project.dd.activity.attraction.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.attraction.domain.BookUserDTO;
import com.project.dd.activity.attraction.service.AttractionService;
import com.project.dd.login.domain.CustomUser;

@Controller
@RequestMapping(value = "/member/activity/attraction/reservation")
public class MemberAttractionController {

	@Autowired
	private AttractionService service;
	
	@GetMapping(value = "/add.do")
	public String add(Model model, String seq) {
		
		//해당 어트랙션 정보 가져오기
		AttractionDTO dto = service.getAttraction(seq);
		
		model.addAttribute("dto", dto);
		
		return "member/activity/attraction/reservation/add";
	}
	
	@PostMapping(value = "/addok.do")
	public String addok(Model model, BookUserDTO dto, Authentication auth) {

		dto.setUser_seq(((CustomUser)auth.getPrincipal()).getDto().getUser_seq());
		
		int result = service.addAttractionBook(dto);
		
		if (result > 0) {
			return "redirect:/user/activity/attraction/detail.do?seq=" + dto.getAttraction_seq();
		} else {
			return "redirect:/member/activity/attraction/add.do";
		}
		
	}
	
}
