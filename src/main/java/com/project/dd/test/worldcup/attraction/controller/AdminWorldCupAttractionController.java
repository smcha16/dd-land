package com.project.dd.test.worldcup.attraction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.dd.test.worldcup.attraction.service.WorldCupAttractionService;

@Controller
public class AdminWorldCupAttractionController {

    @Autowired
    private WorldCupAttractionService attractionService;
    
	@GetMapping(value = "/admin/test/worldcup/view.do")
	public String view(Model model) {

		return "admin/test/worldcup/view";
	}
	
}
