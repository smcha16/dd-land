package com.project.dd.mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MemberMypageController {

	@GetMapping(value = "/member/mypage/view.do")
	public String name(Model model) {

		return "mypage";
	}
}
