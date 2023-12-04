package com.project.dd.mypage.voc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/mypage/inquiry/voc")
public class MemberMypageVOCController {
	
	@GetMapping(value = "/view.do")
	public String view(Model model) {

		return "mypage/inquiry/voc/view";
	}

}
