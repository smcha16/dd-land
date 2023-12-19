package com.project.dd.communication.lost.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.communication.lost.domain.LostPropertyDTO;
import com.project.dd.communication.lost.service.LostPropertyService;

/**
 * 사용자용 분실물 컨트롤러 클래스입니다.
 * 
 * @author sumin
 */
@Controller
@RequestMapping("/user/communication/lost-property")
public class UserLostController {
	
	@Autowired
	private LostPropertyService service;
	
	/**
	 * 분실물 목록을 보여주는 메서드입니다.
	 *
	 * @param category 분실물 카테고리
	 * @param word 검색어
	 * @param start 검색 시작일
	 * @param end 검색 종료일
	 * @param page 페이지 번호
	 * @param model Spring의 Model 객체
	 * @return 분실물 목록을 보여주는 뷰의 경로
	 */
	@GetMapping(value = "/view.do")
	public String view(String category, String word, String start, String end, @RequestParam(defaultValue = "1") int page, Model model) {
		
		String searchStatus = (word == null || word.equals("")) && ((start == null && end == null) || (start.equals("") && end.equals(""))) ? "n" : "y";
		
		Map<String, String> map = service.paging(searchStatus, category, word, start, end, page);
		
		List<LostPropertyDTO> list = service.getLostPropertyList(map);
		
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("list", list);

		return "user/communication/lost-property/view";

	}

}
