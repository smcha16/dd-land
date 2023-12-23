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
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {
		
		//User 전용 페이징
		Map<String, String> map = service.userPaging(page);
		
		//Attraction '정상운영' 목록
		List<AttractionDTO> openList = service.getOpenAttractionList(map);
		
		//Attraction '운휴' 목록
		List<AttractionDTO> closeList = service.getCloseAttractionList();
		
		//페이징
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		
		//개행 확인
		System.out.println("openList 개행 확인: " + openList.toString());
		System.out.println("closeList 개행 확인: " + closeList.toString());
		
		//운영/운휴 Attraction List, 운휴 개수 전달
		model.addAttribute("openList", openList);
		model.addAttribute("closeList", closeList);
		model.addAttribute("closeCount", closeList.size());

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
		
		//개행 확인
		System.out.println("user detail: " + dto.toString());
		
		model.addAttribute("dto", dto);
		
		return "user/activity/attraction/detail";
	}
}
