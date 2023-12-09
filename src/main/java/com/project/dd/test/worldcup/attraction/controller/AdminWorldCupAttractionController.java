package com.project.dd.test.worldcup.attraction.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.test.worldcup.attraction.service.WorldCupAttractionService;

/**
 * 관리자 월드컵 어트랙션 컨트롤러 클래스입니다.
 * 
 * @author 이승원
 */
@Controller
public class AdminWorldCupAttractionController {

	@Autowired
	private WorldCupAttractionService attractionService;

	/**
	 * 어트랙션 리스트를 화면에 페이징하여 표시합니다.
	 * 
	 * @param page 현재 페이지 번호
	 * @param model	모델 객체
	 * @param close	닫힌 어트랙션 여부
	 * @param isTest 테스트 채택 여부
	 * @return 어트랙션 리스트 화면
	 */
	@GetMapping(value = "/admin/test/worldcup/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, Model model,
			@RequestParam(defaultValue = "n") String close, @RequestParam(defaultValue = "Y") String isTest) {

		// 어트랙션 리스트 페이징
		Map<String, String> map = attractionService.paging(page);
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);

		// 어트랙션 리스트
		model.addAttribute("listAttraction", attractionService.getAllAttraction(map));

		return "admin/test/worldcup/view";
	}

	/**
	 * 어트랙션 테스트 채택을 업데이트합니다.
	 * 선택된 어트랙션의 상태를 업데이트하고 페이지를 리다이렉트합니다.
	 * 
	 * @param attractionSeq 어트랙션 일련번호
	 * @param isTest 테스트 채택 여부
	 * @param model 모델 객체
	 * @return 어트랙션 리스트 화면으로 리다이렉트
	 */
	@PostMapping(value = "/admin/test/worldcup/view.do")
	public String updateAttractionStatus(@RequestParam String attractionSeq,
			@RequestParam String isTest, Model model) {
		// System.out.println("seq:" + attractionSeq + " check:" + isTest);

		// Map에 업데이트할 어트랙션 정보를 담아 서비스로 전달
		Map<String, String> map = new HashMap<>();
		map.put("isTest", isTest);
		map.put("attractionSeq", attractionSeq);

		attractionService.updateAttractionStatus(map);

		return "redirect:/admin/test/worldcup/view.do";
	}

}
