package com.project.dd.guide.controller;

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
import com.project.dd.guide.domain.ConvenientDTO;
import com.project.dd.guide.service.ConvenientService;

@Controller
@RequestMapping(value = "/admin/convenient")
public class AdminConvenientController {
	
	@Autowired
	private ConvenientService service;
	
	//목록보기
	@GetMapping(value = "/view.do")
	public String view(String category, String word, @RequestParam(defaultValue = "1") int page, Model model) {
		
		String solting = "admin";
		
		String searchStatus = (word == null || word.equals("")) ? "n" : "y";
		
		Map<String, String> map = service.paging(solting, searchStatus, category, word, page);  //페이징
		
		List<ConvenientDTO> list = service.list(map);   
		
		model.addAttribute("currentPage", page);  //페이징
		model.addAttribute("map", map);  //페이징
		model.addAttribute("list", list);
		
		return "admin/convenient/view";
	}
	
	//추가하기
	@GetMapping(value = "/add.do")
	public String add(Model model) {
		return "admin/convenient/add";
	}
	
	@PostMapping(value = "/addok.do")
	public String addok(Model model, ConvenientDTO dto, HttpServletRequest req, MultipartFile image) {
		
		ConvenientDTO conv = service.addFile(dto, req, image);
		
		int result = service.addConv(conv);
		
		if (result == 1) { 
	 		return "redirect:/admin/convenient/view.do";
	 	} else 
	 		return "redirect:/admin/convenient/add.do";
	}
	
	//삭제하기
	@PostMapping(value = "/del.do")
	public String del(String[] convenient_seq) {

		//편의시설 삭제 -> 편의시설 삭제, 위치 삭제
		
		int result = service.delConv(convenient_seq);
		
		if (result > 0) {
			return "redirect:/admin/convenient/view.do";
		} else {
			return "redirect:/admin/convenient/view.do";
		}
	}
	
	//수정하기
	@GetMapping(value = "/edit.do")
	public String edit(Model model, String seq) {
		
		ConvenientDTO dto = service.one(seq);
		
		model.addAttribute("dto", dto);

		return "admin/convenient/edit";
	}
	
	@PostMapping(value = "/editok.do")
	public String editok(ConvenientDTO dto, HttpServletRequest req, MultipartFile image) {

		ConvenientDTO convenient = service.editFile(dto, req, image);

		int result = service.editConv(convenient);
		
		if (result == 1) {

	 		return "redirect:/admin/convenient/view.do";
	 
	 	} else {
	 
	 		return "redirect:/admin/convenient/edit.do";
	 
	 	}
	}


	
	



	
}
