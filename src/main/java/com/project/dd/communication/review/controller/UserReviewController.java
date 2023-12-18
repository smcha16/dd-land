package com.project.dd.communication.review.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
@RequestMapping("/user/communication/review")
public class UserReviewController {
	
	@Autowired
	private ReviewService service;
	
	/* 목록 */
	
	@GetMapping(value = "/view.do")
	public String view(Authentication auth, HttpSession session, @RequestParam(defaultValue = "n") String order, @RequestParam(defaultValue = "1") int page, Model model) {
		
		String solting = "user";
		
		session.setAttribute("read", "n");
		
		String searchStatus = "n";
		String word = null;
		
		Map<String, String> map = service.paging(solting, searchStatus, word, page);

		map.put("order", order);
		
		List<ReviewDTO> list = service.getReviewList(map);

		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("list", list);

		return "user/communication/review/view";

	}
	
	/* 상세 */
	
	@GetMapping(value = "/detail.do")
	public String detail(HttpSession session, String seq, Model model) {

		service.updateReadCount(session, seq);

		ReviewDTO dto = service.getReview(seq);

		model.addAttribute("dto", dto);

		return "user/communication/review/detail";

	}

}
