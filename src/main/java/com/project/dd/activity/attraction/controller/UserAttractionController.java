package com.project.dd.activity.attraction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.attraction.domain.AttractionImgDTO;
import com.project.dd.activity.attraction.mapper.AttractionMapper;
import com.project.dd.activity.attraction.service.AttractionService;

@Controller
@RequestMapping(value = "/user/activity/attraction")
public class UserAttractionController {

	@Autowired
	private AttractionService service;
	
	@GetMapping(value = "/view.do")
	public String view(Model model, @RequestParam(defaultValue = "n") String close) {
		
		//Attraction 목록(금일 기준 운영 & 운영종료 제외)
		List<AttractionDTO> list = service.getAttractionList(close);
		
		model.addAttribute("list", list);
		model.addAttribute("close", close);

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
		
		//질문!!!!!!!! AttractionDTO에 ilist 담는 과정도 컨트롤러가 아닌 서비스에서 진행해야 하는가?
		
		model.addAttribute("dto", dto);
		
		return "user/activity/attraction/detail";
	}
}
