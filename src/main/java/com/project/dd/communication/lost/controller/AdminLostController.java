package com.project.dd.communication.lost.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.communication.lost.domain.LostPropertyDTO;
import com.project.dd.communication.lost.service.LostPropertyService;

@Controller
@RequestMapping("/admin/communication/lost-property")
public class AdminLostController {
	
	@Autowired
	private LostPropertyService service;
	
	@GetMapping(value = "/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {
		
		Map<String, String> map = service.paging(page);
		
		List<LostPropertyDTO> list = service.getLostPropertyList(map);
		
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("list", list);

		return "admin/communication/lost-property/view";

	}

}
