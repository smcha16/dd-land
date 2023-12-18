package com.project.dd.test.mbti.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.test.mbti.domain.MBTIDTO;
import com.project.dd.test.mbti.service.MBTIService;

@Controller
public class UserMBTIController {

	@Autowired
	private MBTIService mbtiService;

	@GetMapping(value = "/user/test/mbti/view.do")
	public String view(String word, @RequestParam(defaultValue = "1") int page, Model model) {

		String solting = "admin";
		String searchStatus = (word == null || word.equals("")) ? "n" : "y";

		Map<String, String> map = mbtiService.paging(solting, searchStatus, word, page, 9); // 페이징

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
