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
@RequestMapping("/user/communication/lost-property")
public class UserLostController {
	
	@Autowired
	private LostPropertyService service;
	
	/* 목록 */
	
	@GetMapping(value = "/view.do")
	public String view(String category, String word, String start, String end, @RequestParam(defaultValue = "1") int page, Model model) {
		
		String searchStatus = (word == null || word.equals("")) && ((start == null && end == null) || (start.equals("") && end.equals(""))) ? "n" : "y";
		
		Map<String, String> map = service.paging(searchStatus, category, word, start, end, page);
		
		List<LostPropertyDTO> list = service.getLostPropertyList(map);
		
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("list", list);

		return "user/communication/lost-property/view";

	}

}
