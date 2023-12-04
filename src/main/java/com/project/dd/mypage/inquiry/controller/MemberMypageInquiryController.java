package com.project.dd.mypage.inquiry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/mypage/inquiry/inquiry")
public class MemberMypageInquiryController {
	
	@GetMapping(value = "/view.do")
	public String view(Model model) {

		return "mypage/inquiry/inquiry/view";
	}

}
