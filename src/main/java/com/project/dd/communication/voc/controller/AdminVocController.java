package com.project.dd.communication.voc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.communication.voc.domain.VocDTO;
import com.project.dd.communication.voc.service.VocService;

@Controller
@RequestMapping("/admin/communication/voc")
public class AdminVocController {
	
	@Autowired
	private VocService service;
	
/* 목록 */
	
	@GetMapping(value = "/view.do")
	public String view(String word, @RequestParam(defaultValue = "1") int page, Model model) {
		
		String searchStatus = (word == null || word.equals("")) ? "n" : "y";
		
		Map<String, String> map = service.paging(searchStatus, word, page);
		
		List<VocDTO> list = service.getVocList(map);
		
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("list", list);

		return "admin/communication/voc/view";

	}
	
	/* 답변 */
	
	@PostMapping(value = "/answer.do")
	public String answer(VocDTO dto) {
		
		service.editAnswer(dto);

	 	return "redirect:/admin/communication/voc/view.do";

	}

}
