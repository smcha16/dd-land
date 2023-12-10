package com.project.dd.communication.notice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.communication.notice.domain.NoticeDTO;
import com.project.dd.communication.notice.service.NoticeService;

@Controller
@RequestMapping("/admin/communication/notice")
public class AdminNoticeController {
	
	@Autowired
	private NoticeService service;
	
	@GetMapping(value = "/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {

		Map<String, String> map = service.paging(page);

		List<NoticeDTO> list = service.getNoticeList(map);

		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("list", list);

		return "admin/communication/notice/view";

	}
	
	@GetMapping(value = "/add.do")
	public String add(Model model) {

		return "admin/communication/notice/add";

	}
	
	@PostMapping(value = "/addok.do")
	public String addOk(Model model) {

		return "addok";

	}

}
