package com.project.dd.pb.benefit.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.pb.benefit.domain.BenefitDTO;
import com.project.dd.pb.benefit.service.BenefitService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user/pb/benefit")
@RequiredArgsConstructor
public class UserBenefitController {
	
	private final BenefitService service;

	@GetMapping("/view.do")
	public String list(Model model) {
		
		List<BenefitDTO> normalList = service.normalList(); // 일반혜택
		List<BenefitDTO> cardList = service.cardList(); // 카드/통신사 혜택
		List<BenefitDTO> list = service.List();
		
		model.addAttribute("normalList",normalList);
		model.addAttribute("cardList",cardList);
		model.addAttribute("list",list);
		
		return "user/pb/benefit/view";
		
	}

}
