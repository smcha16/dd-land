package com.project.dd.communication.faq.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.communication.faq.domain.FaqDTO;
import com.project.dd.communication.faq.service.FaqService;

@Controller
@RequestMapping("/user/communication/faq")
public class UserFaqController {
	
	@Autowired
	private FaqService service;
	
	/* 목록 */
	
	@GetMapping(value = "/view.do")
	public String view(@RequestParam(defaultValue = "이용정보") String type, String word, @RequestParam(defaultValue = "1") int page, Model model) {

		String searchStatus = (word == null || word.equals("")) ? "n" : "y";
		
		Map<String, String> map = service.paging(type, searchStatus, word, page);
		
		List<FaqDTO> list = service.getFaqList(map);

		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("list", list);

		return "user/communication/faq/view";

	}

}
