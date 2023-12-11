package com.project.dd.communication.lost.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.communication.lost.domain.LostPropertyDTO;
import com.project.dd.communication.lost.service.LostPropertyService;

@Controller
@RequestMapping("/admin/communication/lost-property")
public class AdminLostController {
	
	@Autowired
	private LostPropertyService service;
	
	// 목록
	
	@GetMapping(value = "/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {
		
		Map<String, String> map = service.paging(page);
		
		List<LostPropertyDTO> list = service.getLostPropertyList(map);
		
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("list", list);

		return "admin/communication/lost-property/view";

	}
	
	// 추가
	
	@GetMapping(value = "/add.do")
	public String add() {

		return "admin/communication/lost-property/add";

	}
	
	@PostMapping(value = "/addok.do")
	public String addOk(LostPropertyDTO dto, HttpServletRequest req, MultipartFile doc) {
		
		LostPropertyDTO lost = service.addFile(dto, req, doc);

		int result = service.addLostProperty(lost);
		
		if (result == 1) {

	 		return "redirect:/admin/communication/lost-property/view.do";
	 
	 	} else {
	 
	 		return "redirect:/admin/communication/lost-property/add.do";
	 
	 	}

	}

}
