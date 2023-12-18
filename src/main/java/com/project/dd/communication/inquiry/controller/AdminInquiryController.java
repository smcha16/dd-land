package com.project.dd.communication.inquiry.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.communication.inquiry.domain.InquiryDTO;
import com.project.dd.communication.inquiry.service.InquiryService;

@Controller
@RequestMapping("/admin/communication/inquiry")
public class AdminInquiryController {
	
	@Autowired
	private InquiryService service;
	
	/* 목록 */
	
	@GetMapping(value = "/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {
		
		Map<String, String> map = service.paging(page);
		
		List<InquiryDTO> list = service.getInquiryList(map);
		
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("list", list);

		return "admin/communication/inquiry/view";

	}
	
	/* 답변 */
	
	@PostMapping(value = "/answer.do")
	public String answer(InquiryDTO dto) {
		
		service.editAnswer(dto);

	 	return "redirect:/admin/communication/inquiry/view.do";

	}

}
