package com.project.dd.guide.convenient.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.guide.convenient.domain.ConvenientDTO;
import com.project.dd.guide.convenient.service.ConvenientService;


@Controller
@RequestMapping(value = "/user/guide/convenient")
public class UserConvenientController {
	
	@Autowired
	private ConvenientService convenientService;   //ConvenientService 객체 생성
	
	@GetMapping(value = "/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {
		
		Map<String, String> map = convenientService.paging(page);  //페이징
		
		List<ConvenientDTO> list = convenientService.list(map);   //편의시설 list불러오는 것을 service에게 위임
		
		model.addAttribute("currentPage", page);  //페이징
		model.addAttribute("map", map);  //페이징
		model.addAttribute("list", list);
		
		return "user/guide/convenient/view";
	}

	
	@GetMapping(value = "/detail.do")
	public String detail(Model model, String seq) {  //detail 보여줄 seq같이 가져오기
		
		ConvenientDTO dto = convenientService.one(seq);   //선택한 편의시설 데이터 1개
		
		
		return "user/guide/convenient/detail";
	}

}
