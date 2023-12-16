package com.project.dd.guide.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.guide.domain.ConvenientDTO;
import com.project.dd.guide.service.ConvenientService;

@Controller
@RequestMapping(value = "/admin/convenient")
public class AdminConvenientController {
	
	@Autowired
	private ConvenientService service;
	
	@GetMapping(value = "/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {
		
		Map<String, String> map = service.paging(page);  //페이징
		
		List<ConvenientDTO> list = service.list(map);   
		
		model.addAttribute("currentPage", page);  //페이징
		model.addAttribute("map", map);  //페이징
		model.addAttribute("list", list);
		
		return "admin/convenient/view";
	}

	
}
