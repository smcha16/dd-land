package com.project.dd.close.theater.controller;

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
import com.project.dd.close.theater.domain.CloseTheaterDTO;
import com.project.dd.close.theater.service.CloseTheaterService;

@Controller
@RequestMapping(value = "/admin/close/theater")
public class AdminCloseTheaterController {

	@Autowired
	private CloseTheaterService service;
	
	//목록보기
	@GetMapping(value = "/view.do") 
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {
			
		Map<String, String> map = service.paging(page);  //페이징
			
		List<CloseTheaterDTO> list = service.list(map); 
			
		model.addAttribute("currentPage", page);  //페이징
		model.addAttribute("map", map);  //페이징
		model.addAttribute("list", list);
			
		return "admin/close/theater/view"; 
	}
		
	//추가하기
	@GetMapping(value = "/add.do")
	public String add(Model model) {
			
		model.addAttribute("theaterlist", service.theaterlist());  

		return "admin/close/theater/add";
	}
		
	@PostMapping(value = "/addok.do")
	public String addok(CloseTheaterDTO dto) {

		//System.out.println(dto);
			
		int result = service.addCloseTheater(dto);
			
		if (result > 0) {
			return "redirect:/admin/close/theater/view.do";
		} else {  //실패
			return "redirect:/admin/close/theater/add.do";
		}
			
	}

	//수정하기
	@GetMapping(value = "/edit.do")
	public String edit(Model model, String seq) {
			
		CloseTheaterDTO dto = service.getOne(seq); 
			
		//System.out.println(dto);
			
		model.addAttribute("dto", dto);
			
		return "admin/close/theater/edit";
	}
		
	@PostMapping(value = "/editok.do")
	public String editok(CloseTheaterDTO dto) {

		//System.out.println(dto);
		int result = service.editClose(dto);
					
		if (result == 1) {
		 	return "redirect:/admin/close/theater/view.do";
		} else 
		 	return "redirect:/admin/close/theater/edit.do";
	}
		
	//삭제하기
	@PostMapping(value = "/del.do")
	public String del(Model model, String[] closeTheater_seq) {

		service.del(closeTheater_seq);
			
		return "redirect:/admin/close/theater/view.do";
	}
	
}
