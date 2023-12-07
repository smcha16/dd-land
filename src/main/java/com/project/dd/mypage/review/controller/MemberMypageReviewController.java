package com.project.dd.mypage.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.mypage.review.domain.ReviewDTO;
import com.project.dd.mypage.review.service.MypageReviewService;

@Controller
@RequestMapping("/member/mypage/review")
public class MemberMypageReviewController {
	
	@Autowired
	private MypageReviewService service;

	@GetMapping(value = "/view.do")
	public String view(Model model) {
		
		List<ReviewDTO> list = service.list();
		
		model.addAttribute("list", list);

		return "mypage/review/view";
	}

}
