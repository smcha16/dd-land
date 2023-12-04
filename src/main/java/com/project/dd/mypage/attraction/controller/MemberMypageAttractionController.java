package com.project.dd.mypage.attraction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/mypage/attraction")
public class MemberMypageAttractionController {
	
	@GetMapping(value = "/view.do")
	public String view(Model model) {

		return "mypage/attraction/view";
	}

}
