package com.project.dd.communication.faq.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.communication.faq.domain.FaqDTO;
import com.project.dd.communication.faq.service.FaqService;

@Controller
@RequestMapping("/admin/communication/faq")
public class AdminFaqController {
	
	@Autowired
	private FaqService service;
	
	// 목록
	
	@GetMapping(value = "/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {
		
		String type = "없음";

		Map<String, String> map = service.paging(type, page);
		
		List<FaqDTO> list = service.getFaqList(map);

		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("list", list);

		return "admin/communication/faq/view";

	}
	
	// 추가
	
	@GetMapping(value = "/add.do")
	public String add() {

		return "admin/communication/faq/add";

	}
	
	@PostMapping(value = "/addok.do")
	public String addOk(FaqDTO dto) {

		int result = service.addFaq(dto);
		
		if (result == 1) {

	 		return "redirect:/admin/communication/faq/view.do";
	 
	 	} else {
	 
	 		return "redirect:/admin/communication/faq/add.do";
	 
	 	}

	}
	
	// 수정
	
	@GetMapping(value = "/edit.do")
	public String edit(String seq, Model model) {
		
		FaqDTO dto = service.getFaq(seq);
		
		model.addAttribute("dto", dto);

		return "admin/communication/faq/edit";

	}
	
	@PostMapping(value = "/editok.do")
	public String editOk(FaqDTO dto) {

		int result = service.editFaq(dto);
		
		if (result == 1) {

	 		return "redirect:/admin/communication/faq/view.do";
	 
	 	} else {
	 
	 		return "redirect:/admin/communication/faq/edit.do";
	 
	 	}

	}
	
	// 삭제	
	
	@PostMapping(value = "/del.do")
	public String del(String[] seqList) {

	    service.deleteFaq(seqList);

	    return "redirect:/admin/communication/faq/view.do";
	    
	}

}
