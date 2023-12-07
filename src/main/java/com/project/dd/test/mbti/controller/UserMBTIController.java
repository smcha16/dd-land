package com.project.dd.test.mbti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.dd.test.mbti.service.MBTIService;

@Controller
public class UserMBTIController {

    @Autowired
    private MBTIService mbtiService;
    
	@GetMapping(value = "/user/test/mbti/view.do")
	public String view(Model model) {

        model.addAttribute("listMBTI", mbtiService.getAllMBTI());
		
		return "user/test/mbti/view";
	}
	
}
