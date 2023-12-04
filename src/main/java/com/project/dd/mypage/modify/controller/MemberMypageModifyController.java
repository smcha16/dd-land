package com.project.dd.mypage.modify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/mypage/modify")
public class MemberMypageModifyController {
	
	@GetMapping(value = "/edit.do")
	public String eidt(Model model) {

		return "mypage/modify/edit";
	}

}
