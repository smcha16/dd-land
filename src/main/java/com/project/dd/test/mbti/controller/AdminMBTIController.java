package com.project.dd.test.mbti.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.test.mbti.service.MBTIService;

@Controller
public class AdminMBTIController {

    @Autowired
    private MBTIService mbtiService;
    
	@GetMapping(value = "/admin/test/mbti/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {
		
		Map<String, String> map = mbtiService.paging(page);

		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("listMBTI", mbtiService.getAllMBTI(map));
		
		return "admin/test/mbti/view";
	}
	
}
