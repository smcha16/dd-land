package com.project.dd.close.attraction.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.close.attraction.domain.CloseAttractionDTO;
import com.project.dd.close.attraction.service.CloseAttractionService;


@Controller
@RequestMapping(value="/admin/close/attraction")
public class AdminCloseAttractionController {
	
	@Autowired private CloseAttractionService closeAttrService;
	 
	//목록보기
	@GetMapping(value = "/view.do") 
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {
		
		Map<String, String> map = closeAttrService.paging(page);  //페이징
		
		List<CloseAttractionDTO> list = closeAttrService.list(map); 
		
		model.addAttribute("currentPage", page);  //페이징
		model.addAttribute("map", map);  //페이징
		model.addAttribute("list", list);
		
		return "admin/close/attraction/view"; 
	}
	
	//추가하기
	@GetMapping(value = "/add.do")
	public String add(Model model) {
		
		model.addAttribute("attlist", closeAttrService.attlist());  //추가할 어트랙션 리스트 가져오기

		return "admin/close/attraction/add";
	}
	
	@PostMapping(value = "/addok.do")
	public String addok(CloseAttractionDTO dto) {

		//System.out.println(dto);
		
		int result = closeAttrService.addCloseAtt(dto);
		
		if (result > 0) {
			return "redirect:/admin/close/attraction/view.do";
		} else {  //실패
			return "redirect:/admin/close/attraction/add.do";
		}
		
	}

	//수정하기
	@GetMapping(value = "/edit.do")
	public String edit(Model model, String seq) {
		
		CloseAttractionDTO dto = closeAttrService.getOne(seq); 
		
		//System.out.println(dto);
		
		model.addAttribute("dto", dto);
		
		return "admin/close/attraction/edit";
	}
	
	@PostMapping(value = "/editok.do")
	public String editok(CloseAttractionDTO dto) {

		//System.out.println(dto);
		int result = closeAttrService.editClose(dto);
		
		if (result == 1) {
	 		return "redirect:/admin/close/attraction/view.do";
	 	} else 
	 		return "redirect:/admin/close/attraction/edit.do";
	}
	
	//삭제하기
	@PostMapping(value = "/del.do")
	public String del(Model model, String[] closeAttraction_seq) {

		closeAttrService.del(closeAttraction_seq);
		
		return "redirect:/admin/close/attraction/view.do";
	}


	 

}
