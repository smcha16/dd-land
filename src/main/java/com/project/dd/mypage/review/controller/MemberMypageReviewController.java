package com.project.dd.mypage.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/mypage/review")
public class MemberMypageReviewController {

	@GetMapping(value = "/view.do")
	public String view(Model model) {

		return "mypage/review/view";
	}

}
