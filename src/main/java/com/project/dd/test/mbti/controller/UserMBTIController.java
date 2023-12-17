package com.project.dd.test.mbti.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.attraction.domain.AttractionImgDTO;
import com.project.dd.activity.attraction.service.AttractionService;
import com.project.dd.test.mbti.domain.MBTIDTO;
import com.project.dd.test.mbti.service.MBTIService;

@Controller
public class UserMBTIController {

	@Autowired
	private MBTIService mbtiService;

	@Autowired
	private AttractionService attractionService;
	
	@GetMapping(value = "/user/test/mbti/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {

		// 페이징
		Map<String, String> map = mbtiService.paging(page, 9);

		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("listMBTI", mbtiService.getAllMBTI(map));

		return "user/test/mbti/view";
	}

	@GetMapping(value = "/user/test/mbti/detail.do")
	public String detail(Model model, @RequestParam String mbti_seq) {	
		
		// MBTI
		MBTIDTO dto = mbtiService.getMBTI(mbti_seq);
		model.addAttribute("dto", dto);

		return "user/test/mbti/detail";
	}

}
