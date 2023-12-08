package com.project.dd.communication.review.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.communication.review.domain.ReviewDTO;
import com.project.dd.communication.review.service.ReviewService;

@Controller
@RequestMapping("/user/communication/review")
public class UserReviewController {
	
	@Autowired
	private ReviewService service;
	
	@GetMapping(value = "/view.do")
	public String view(HttpSession session, @RequestParam(defaultValue = "n") String order, @RequestParam(defaultValue = "1") int page, Model model) {
		
		session.setAttribute("read", "n");
		
		Map<String, String> map = service.paging(order, page);
		
		List<ReviewDTO> list = service.getReviewList(map);

		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("list", list);

		return "user/communication/review/view";

	}
	
	@GetMapping(value = "/detail.do")
	public String detail(HttpSession session, String seq, Model model) {

		service.updateReadCount(session, seq);

		ReviewDTO dto = service.getReview(seq);

		model.addAttribute("dto", dto);

		return "user/communication/review/detail";

	}

}
