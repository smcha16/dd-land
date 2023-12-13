package com.project.dd.communication.notice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.communication.notice.domain.NoticeDTO;
import com.project.dd.communication.notice.service.NoticeService;

@Controller
@RequestMapping("/user/communication/notice")
public class UserNoticeController {
	
	@Autowired
	private NoticeService service;
	
	/* 목록 */
	
	@GetMapping(value = "/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {

		Map<String, String> map = service.paging(page);

		List<NoticeDTO> list = service.getNoticeList(map);

		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("list", list);

		return "user/communication/notice/view";

	}
	
	/* 상세 */
	
	@GetMapping(value = "/detail.do")
	public String detail(String seq, Model model) {
		
		NoticeDTO dto = service.getNotice(seq);
		
		model.addAttribute("dto", dto);

		return "user/communication/notice/detail";

	}

}
