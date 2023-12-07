package com.project.dd.guide.convenient.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.guide.convenient.domain.ConvenientDTO;
import com.project.dd.guide.convenient.service.ConvenientService;


@Controller
@RequestMapping(value = "/user/guide/convenient")
public class UserConvenientController {
	
	@Autowired
	private ConvenientService convenientService;   //ConvenientService 객체 생성
	
	@GetMapping(value = "/view.do")
	public String view(Model model) {
		
		List<ConvenientDTO> list = convenientService.list();   //편의시설 list불러오는 것을 service에게 위임
		
		model.addAttribute("list", list);
		
		return "user/guide/convenient/view";
	}

	
	/*
	 * 
	 * 
	 * @GetMapping(value = "/view.do") public String view(Model model) {
	 * List<ConvenientDTO> list = mapper.list();
	 * 
	 * model.addAttribute("list", list);
	 * 
	 * return "user/guide/convenient/view"; }
	 * 
	 * @GetMapping(value = "/detail.do") public String detail(Model model) {
	 * //seq가져오기
	 * 
	 * return "user/guide/convenient/detail"; }
	 */


	
	
}
