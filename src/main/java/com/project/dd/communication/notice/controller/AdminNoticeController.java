package com.project.dd.communication.notice.controller;

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

import com.project.dd.communication.notice.domain.NoticeDTO;
import com.project.dd.communication.notice.service.NoticeService;

@Controller
@RequestMapping("/admin/communication/notice")
public class AdminNoticeController {
	
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

		return "admin/communication/notice/view";

	}
	
	/* 추가 */
	
	@GetMapping(value = "/add.do")
	public String add() {

		return "admin/communication/notice/add";

	}
	
	@PostMapping(value = "/addok.do")
	public String addOk(NoticeDTO dto, HttpServletRequest req, MultipartFile doc) {
		
		NoticeDTO notice = service.addFile(dto, req, doc);

		int result = service.addNotice(notice);
		
		if (result == 1) {

	 		return "redirect:/admin/communication/notice/view.do";
	 
	 	} else {
	 
	 		return "redirect:/admin/communication/notice/add.do";
	 
	 	}

	}
	
	/* 수정 */
	
	@GetMapping(value = "/edit.do")
	public String edit(String seq, Model model) {
		
		NoticeDTO dto = service.getNotice(seq);
		
		model.addAttribute("dto", dto);

		return "admin/communication/notice/edit";

	}
	
	@PostMapping(value = "/editok.do")
	public String editOk(NoticeDTO dto, HttpServletRequest req, MultipartFile doc) {
		
		NoticeDTO notice = service.editFile(dto, req, doc);

		int result = service.editNotice(notice);
		
		if (result == 1) {

	 		return "redirect:/admin/communication/notice/view.do";
	 
	 	} else {
	 
	 		return "redirect:/admin/communication/notice/edit.do";
	 
	 	}

	}
	
	/* 삭제 */
	
	@PostMapping(value = "/del.do")
	public String del(String[] seqList) {

	    service.deleteNotice(seqList);

	    return "redirect:/admin/communication/notice/view.do";

	    
	}

}
