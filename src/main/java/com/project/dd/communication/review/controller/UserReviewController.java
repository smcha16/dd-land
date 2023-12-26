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

/**
 * 사용자용 리뷰 컨트롤러 클래스입니다.
 * 
 * @author sumin
 */
@Controller
@RequestMapping("/user/communication/review")
public class UserReviewController {
	
	@Autowired
	private ReviewService service;
	
	/**
	 * 리뷰 목록을 보여주는 메서드입니다.
	 *
	 * @param auth 인증 객체
	 * @param session 세션 객체
	 * @param order 정렬 순서
	 * @param page 페이지 번호
	 * @param model Spring의 Model 객체
	 * @return 리뷰 목록을 보여주는 뷰의 경로
	 */
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
	
	/**
	 * 특정 리뷰의 상세 내용을 보여주는 메서드입니다.
	 *
	 * @param session 세션 객체
	 * @param seq 리뷰의 일련번호
	 * @param model Spring의 Model 객체
	 * @return 특정 리뷰의 상세 내용을 보여주는 뷰의 경로
	 */
	@GetMapping(value = "/detail.do")
	public String detail(HttpSession session, String seq, Model model) {

		service.updateReadCount(session, seq);

		ReviewDTO dto = service.getReview(seq);

		model.addAttribute("dto", dto);

		return "user/communication/review/detail";

	}

}
