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

@Controller
@RequestMapping(value = "/user/activity/attraction")
public class UserAttractionController {

	@Autowired
	private AttractionService service;
	
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
