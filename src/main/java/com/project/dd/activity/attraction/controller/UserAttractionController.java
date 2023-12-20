package com.project.dd.activity.attraction.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.attraction.domain.AttractionImgDTO;
import com.project.dd.activity.attraction.service.AttractionService;

/**
 * 
 * @author 박나래
 * 
 * 어트랙션 조회(목록, 상세)를 담당하는 모든 회원 전용 컨트롤러 클래스입니다.
 *
 */
@Controller
@RequestMapping(value = "/user/activity/attraction")
public class UserAttractionController {

	@Autowired
	private AttractionService service;
	
	/**
	 * 
	 * 운영중인 어트랙션 목록을 불러오는 메서드입니다.
	 * 
	 * @param word 검색어
	 * @param page 페이지 번호
	 * @param model 객체
	 * @return jsp 파일명
	 */
	@GetMapping(value = "/view.do")
	public String view(String word, @RequestParam(defaultValue = "1") int page, Model model) {
		
		String searchStatus = (word == null || word.equals("")) ? "n" : "y";
		
		//페이징
		String solting = "user";
		Map<String, String> map = service.paging(searchStatus, word, page, solting);
		
		//Attraction 목록(금일 기준 운영 & 운영종료 제외)
		List<AttractionDTO> list = service.getAttractionList(map);
		
		//운휴인 Attraction 
		int closeCount = service.getAttractionCloseCount(list);
		
		//페이징
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		
		//어트 목록, 운휴 어트 개수 전달
		model.addAttribute("list", list);
		model.addAttribute("closeCount", closeCount);

		return "user/activity/attraction/view";
	}

	/**
	 * 
	 * 선택한 특정 어트랙션의 상세 내용을 불러오는 메서드입니다.
	 * 
	 * @param model 객체
	 * @param seq 어트랙션 번호
	 * @return jsp 파일명
	 */
	@GetMapping(value = "/detail.do")
	public String detail(Model model, String seq) {
		
		//Attraction 1개(List<AttractionImgDTO> 제외)
		AttractionDTO dto = service.getAttraction(seq);
		
		//List<AttractionImgDTO> 가져오기
		List<AttractionImgDTO> ilist = service.getAttractionImgList(seq);
		
		//ilist > AttractionDTO에 담기
		dto.setImgList(ilist);
		
		model.addAttribute("dto", dto);
		
		return "user/activity/attraction/detail";
	}
}
