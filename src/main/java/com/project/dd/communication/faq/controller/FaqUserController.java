package com.project.dd.communication.faq.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.communication.faq.mapper.FaqMapper;
import com.project.dd.communication.notice.domain.NoticeDTO;

@Controller
public class FaqUserController {
	
	@Autowired
	private FaqMapper mapper;
	
	@GetMapping(value = "/user/communication/faq/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "이용정보") String type, Model model) {

		Map<String, String> map = new HashMap<String, String>();

		map.put("type", type);
		
		int pageSize = 10;
		
		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));
		
		int totalPosts = mapper.getTotalCount(map);
		int totalPages = (int)Math.ceil((double)totalPosts / pageSize);

		model.addAttribute("list", mapper.getFaqList(map));
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("totalPosts", totalPosts);
		model.addAttribute("totalPages", totalPages);

		return "user/communication/faq/view";

	}

}
