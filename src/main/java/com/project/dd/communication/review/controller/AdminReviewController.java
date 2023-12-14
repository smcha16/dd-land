package com.project.dd.communication.review.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.communication.review.domain.ReviewDTO;
import com.project.dd.communication.review.service.ReviewService;

@Controller
@RequestMapping("/admin/communication/review")
public class AdminReviewController {
	
	@Autowired
	private ReviewService service;
	
	/* 목록 */
	
	@GetMapping(value = "/view.do")
	public String view(Authentication auth, @RequestParam(defaultValue = "1") int page, Model model) {
		
		String order = "n";
		
		Map<String, String> map = service.paging(order, page);
		
		List<ReviewDTO> list = service.getReviewList(auth, map);

		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("list", list);

		return "admin/communication/review/view";

	}

}
