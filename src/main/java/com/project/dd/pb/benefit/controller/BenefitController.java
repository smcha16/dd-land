package com.project.dd.pb.benefit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.dd.pb.benefit.domain.BenefitDTO;
import com.project.dd.pb.benefit.persistence.BenefitDAO;

import lombok.RequiredArgsConstructor;



@Controller
public class BenefitController {
	
	@Autowired
	private BenefitDAO dao;
	
	@GetMapping
	public String list(Model model) {
		
		List<BenefitDTO> list = dao.list();
		
		model.addAttribute("list",list);
		
		return "user/benefit/view";
		
	}
	
	
		
}
