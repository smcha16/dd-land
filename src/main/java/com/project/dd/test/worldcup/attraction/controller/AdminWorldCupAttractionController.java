package com.project.dd.test.worldcup.attraction.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.test.worldcup.attraction.service.WorldCupAttractionService;

/**
 * 관리자가 월드컵 어트랙션 정보를 관리하는 컨트롤러입니다.
 * 
 * 1. 월드컵 어트랙션 목록 조회 및 페이징 기능
 * 2. 월드컵 어트랙션 테스트 진행 여부 업데이트 기능
 * 
 * @author 이승원
 */
@Controller
@RequestMapping("/admin/test/worldcup/attraction")
public class AdminWorldCupAttractionController {

	@Autowired
	private WorldCupAttractionService awcService;

	/**
     * 월드컵 어트랙션 목록을 조회하여 페이징된 결과를 화면에 전달합니다.
     * 
     * @param word  검색어
     * @param page  현재 페이지 번호
     * @param model 화면에 전달할 데이터를 담는 모델 객체
     * @return 월드컵 어트랙션 목록 조회 화면
     */
	@GetMapping(value = "/view.do")
	public String view(String word, @RequestParam(defaultValue = "1") int page, Model model) {

		String solting = "admin";
		String searchStatus = (word == null || word.equals("")) ? "n" : "y";

		// 페이징 정보를 담은 Map 생성
		Map<String, String> map = awcService.paging(solting, searchStatus, word, page);

		// 화면에 전달할 데이터 설정
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("listAttraction", awcService.getAllAttraction(map));
		model.addAttribute("awcFinalWinTotalCount", awcService.getAWCFinalWinTotalCount());

		return "admin/test/worldcup/attraction/view";
	}

	/**
     * 선택한 월드컵 어트랙션의 테스트 진행 여부를 업데이트합니다.
     * 
     * @param attractionSeq 선택한 어트랙션의 일련번호
     * @param isTest        업데이트할 테스트 진행 여부
     * @param model         화면에 전달할 데이터를 담는 모델 객체
     * @return              월드컵 어트랙션 목록 조회 화면으로 리다이렉트
     */
	@PostMapping(value = "/view.do")
	public String updateAttractionStatus(@RequestParam String attractionSeq, @RequestParam String isTest, Model model) {
	    //System.out.println("seq:" + attractionSeq + " check:" + isTest);
	    
		// 선택한 어트랙션의 테스트 진행 여부 업데이트
	    Map<String, String> map = new HashMap<>();
	    map.put("isTest", isTest);
	    map.put("attractionSeq", attractionSeq);
	    awcService.updateAttractionStatus(map);
	    
	    // 월드컵 어트랙션 목록 조회 화면으로 리다이렉트
	    return "redirect:/admin/test/worldcup/attraction/view.do"; 
	}

}
