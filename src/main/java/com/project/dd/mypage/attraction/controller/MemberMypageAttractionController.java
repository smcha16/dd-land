package com.project.dd.mypage.attraction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.mypage.attraction.domain.AttractionDTO;
import com.project.dd.mypage.attraction.service.MypageAttractionService;

@Controller
@RequestMapping("/member/mypage/attraction")
public class MemberMypageAttractionController {

	@Autowired
	private MypageAttractionService service;

	@GetMapping(value = "/view.do")
	public String view(Model model) {

		List<AttractionDTO> list = service.list();

		model.addAttribute("list", list);

		return "mypage/attraction/view";
	}

}
