package com.project.dd.communication.faq.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.communication.faq.domain.FaqDTO;
import com.project.dd.communication.faq.service.FaqService;

/**
 * 사용자용 FAQ 컨트롤러 클래스입니다.
 * 
 * @author sumin
 */
@Controller
@RequestMapping("/user/communication/faq")
public class UserFaqController {
	
	@Autowired
	private FaqService service;
	
	/**
	 * FAQ 목록을 보여주는 메서드입니다.
	 *
	 * @param type FAQ 유형
	 * @param word 검색어
	 * @param page 페이지 번호
	 * @param model Spring의 Model 객체
	 * @return FAQ 목록을 보여주는 뷰의 경로
	 */
	@GetMapping(value = "/view.do")
	public String view(@RequestParam(defaultValue = "이용정보") String type, String word, @RequestParam(defaultValue = "1") int page, Model model) {

		String searchStatus = (word == null || word.equals("")) ? "n" : "y";
		
		Map<String, String> map = service.paging(type, searchStatus, word, page);
		
		List<FaqDTO> list = service.getFaqList(map);

		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("list", list);

		return "user/communication/faq/view";

	}

}
