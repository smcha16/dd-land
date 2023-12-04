package com.project.dd.pb.benefit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.pb.benefit.domain.BenefitDTO;


	
	@Controller
	@RequestMapping("/user/pb")
	public class UserBenefitController {
		
		
		@GetMapping("/benefit/list.do")
		public String list(Model model) {
			
			
			
			return "user/benefit/list";
			
		}
		
		
			
	}


