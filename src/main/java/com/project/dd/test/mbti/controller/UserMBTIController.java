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
		Map<String, String> map = mbtiService.paging(page);

		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("listMBTI", mbtiService.getAllMBTI(map));

		return "user/test/mbti/view";
	}

	@GetMapping(value = "/user/test/mbti/detail.do")
	public String detail(Model model, @RequestParam String mbti_seq) {	
		
		// MBTI 1개
		MBTIDTO mdto = mbtiService.getMBTI(mbti_seq);
		
		// Attraction 1개
		AttractionDTO adto = attractionService.getAttraction(mdto.getAttraction_seq());

		// List<AttractionImgDTO> 가져오기
		List<AttractionImgDTO> imglist = attractionService.getAttractionImgList(mdto.getAttraction_seq());

		// imglist > AttractionDTO에 담기
		adto.setImgList(imglist);

		model.addAttribute("mdto", mdto);
		model.addAttribute("adto", adto);

		return "user/test/mbti/detail";
	}

}
