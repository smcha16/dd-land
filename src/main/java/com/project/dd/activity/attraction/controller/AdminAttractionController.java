package com.project.dd.activity.attraction.controller;

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

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.attraction.service.AttractionService;


@Controller
@RequestMapping(value = "/admin/activity/attraction")
public class AdminAttractionController {
	
	@Autowired
	private AttractionService service;
	
	@GetMapping(value = "/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {
		
		//페이징
		String solting = "admin";
		Map<String, String> map = service.paging(page, solting);
		
		//Attraction 목록(운영종료 제외)
		List<AttractionDTO> list = service.getAttractionList(map);
		
		//페이징 전달
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		
		//어트 목록 전달
		model.addAttribute("list", list);
		
		return "admin/activity/attraction/view";
	}
	
	@GetMapping(value = "/add.do")
	public String add(Model model) {
		return "admin/activity/attraction/add";
	}

	@PostMapping(value = "/addok.do")
	public String addok(Model model, AttractionDTO dto, MultipartFile[] imgs, HttpServletRequest req) {
		
		//0. 어트랙션 위치 중복 검사 > ajax
		//1. 어트랙션 추가
		//2. 어트랙션 위치 추가
		//3. 어트랙션 이미지 추가
		
		System.out.println(dto.toString());
		
		
		int result = service.addAttraction(dto, imgs, req);
		
		if (result > 0) {
			return "redirect:/admin/activity/attraction/view.do";
		} else {
			model.addAttribute("alertMessage", "어트랙션 추가에 실패했습니다.");
			return "redirect:/admin/activity/attraction/add.do";
		}
		
	}
	
	@PostMapping(value = "/del.do")
	public String del(Model model, String[] attraction_seq) {

		//어트랙션 삭제 > "(운영종료)" 문구 UPDATE로 구현
		// - tblAttractionImg > DELETE
		// - tblAttractionLocation > DELETE
		// - tblAttraction > UPDATE
		
		int result = service.delAttraction(attraction_seq);
		
		if (result > 0) {
			return "redirect:/admin/activity/attraction/view.do";
		} else {
			model.addAttribute("alertMessage", "어트랙션 삭제에 실패했습니다.");
			return "redirect:/admin/activity/attraction/view.do";
		}
	}
	
	
	

}
