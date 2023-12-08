package com.project.dd.mypage.review.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.login.domain.CustomUser;
import com.project.dd.mypage.review.domain.ReviewDTO;
import com.project.dd.mypage.review.service.MypageReviewService;

@Controller
@RequestMapping("/member/mypage/review")
public class MemberMypageReviewController {
	
	@Autowired
	private MypageReviewService service;

	@GetMapping(value = "/view.do")
	public String view(Model model, Authentication auth, @RequestParam(defaultValue = "1") int page) {
		
		Map<String, String> map = service.paging(page);  //페이징
		
		String email = ((CustomUser) auth.getPrincipal()).getDto().getEmail();
		
		map.put("email", email);
		
		List<ReviewDTO> list = service.list(map);
		
		model.addAttribute("list", list);
		model.addAttribute("email", email);
		model.addAttribute("currentPage", page);  //페이징
	    model.addAttribute("map", map);  //페이징

		return "mypage/review/view";
	}
	
	@PostMapping(value = "/delete.do")
	public String delete(Model model, String selectedReview) {
		
		int imgResult = service.imgDelete(selectedReview);
		int result = service.delete(selectedReview);

		if (result == 1 && imgResult == 1) {

			return "redirect:/member/mypage/review/view.do";

		} else {
			
			System.out.println("review delete error");
			
		}

		return "redirect:/member/mypage/review/view.do";

	}

}
