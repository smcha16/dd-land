package com.project.dd.pb.benefit.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.pb.benefit.domain.BenefitDTO;
import com.project.dd.pb.benefit.service.BenefitService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/pb/benefit")
public class AdminBenefitController {
	
	private final BenefitService service;
		

		
		@GetMapping("/view.do")
		public String view(Model model) {
			
			List<BenefitDTO> list = service.List();
			
			
			model.addAttribute("list",list);
					
			
			return "admin/pb/benefit/view";
		
			
		}
		
		@GetMapping("/add.do")
		public String add(Model model) {
			
			
			return "admin/pb/benefit/add";
		}
		@PostMapping("/add.do")
		public String addok(@Valid BenefitDTO benefitDTO,BindingResult bindingResult) {
			
			if (bindingResult.hasErrors()) {
				return "admin/pb/benefit/add";
			}
			
			
			return "redirect:admin/pb/benefit/view";
		}

}
