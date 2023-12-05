package com.project.dd.communication.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.communication.notice.domain.NoticeDTO;
import com.project.dd.communication.notice.mapper.NoticeMapper;

@Controller
@RequestMapping("/user/communication/notice")
public class NoticeUserController {
	
	@Autowired
	private NoticeMapper mapper;
	
	@GetMapping(value = "/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {

		int pageSize = 10;
		
		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;
		
		Map<String, String> map = new HashMap<String, String>();

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));
		
		int totalPosts = mapper.getTotalCount(map);
		int totalPages = (int)Math.ceil((double)totalPosts / pageSize);
		
		List<NoticeDTO> list = mapper.getNoticeList(map);
		
		for (NoticeDTO dto : list) {
			
			String regdate = dto.getRegdate();
			
			regdate = regdate.substring(0, 10);
			
			dto.setRegdate(regdate);
			
		}

		model.addAttribute("list", list);
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("totalPosts", totalPosts);
		model.addAttribute("totalPages", totalPages);

		return "user/communication/notice/view";

	}
	
	@GetMapping(value = "/detail.do")
	public String detail(String seq, Model model) {
		
		NoticeDTO dto = mapper.getNotice(seq);
		
		String regdate = dto.getRegdate();
		
		regdate = regdate.substring(0, 10);
		
		dto.setRegdate(regdate);
		
		model.addAttribute("dto", dto);

		return "user/communication/notice/detail";

	}

}
