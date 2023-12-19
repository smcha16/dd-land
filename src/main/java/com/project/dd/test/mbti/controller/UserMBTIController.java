package com.project.dd.test.mbti.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.test.mbti.domain.MBTIDTO;
import com.project.dd.test.mbti.service.MBTIService;

/**
 * 사용자가 MBTI별 추천 정보를 조회하는 컨트롤러입니다.
 * 
 * 1. MBTI 테스트 목록 조회하여 페이징된 결과를 화면에 전달
 * 2. 특정 MBTI 테스트의 상세 정보 조회 및 화면에 전달
 * 
 * @author 이승원
 */
@Controller
@RequestMapping("/user/test/mbti")
public class UserMBTIController {

	@Autowired
	private MBTIService mbtiService;

	/**
	 * MBTI 테스트 목록을 조회하여 페이징된 결과를 화면에 전달합니다.
	 * 
	 * @param word  검색어
	 * @param page  현재 페이지 번호
	 * @param model 화면에 전달할 데이터를 담는 모델 객체
	 * @return MBTI 테스트 목록 조회 화면
	 */
	@GetMapping(value = "/view.do")
	public String view(String word, @RequestParam(defaultValue = "1") int page, Model model) {

		String solting = "admin";
		String searchStatus = (word == null || word.equals("")) ? "n" : "y";

		Map<String, String> map = mbtiService.paging(solting, searchStatus, word, page, 9); // 페이징

		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("listMBTI", mbtiService.getAllMBTI(map));

		return "user/test/mbti/view";
	}

	/**
	 * 특정 MBTI 테스트의 상세 정보를 조회하여 화면에 전달합니다.
	 * 
	 * @param model    화면에 전달할 데이터를 담는 모델 객체
	 * @param mbti_seq 조회할 MBTI 테스트의 일련번호
	 * @return MBTI 테스트 상세 정보 조회 화면
	 */
	@GetMapping(value = "/detail.do")
	public String detail(Model model, @RequestParam String mbti_seq) {	
		
		// MBTI
		MBTIDTO dto = mbtiService.getMBTI(mbti_seq);
		model.addAttribute("dto", dto);

		return "user/test/mbti/detail";
	}

}
