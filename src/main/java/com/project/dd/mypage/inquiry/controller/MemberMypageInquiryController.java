package com.project.dd.mypage.inquiry.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.login.domain.CustomUser;
import com.project.dd.mypage.inquiry.domain.InquiryDTO;
import com.project.dd.mypage.inquiry.service.MypageInquiryService;

@Controller
@RequestMapping("/member/mypage/inquiry/inquiry")
public class MemberMypageInquiryController {
	
	@Autowired
	private MypageInquiryService service;
	
	@GetMapping(value = "/view.do")
	public String view(Model model, Authentication auth, @RequestParam(defaultValue = "1") int page) {
		
		Map<String, String> map = service.paging(page);  //페이징
		
		String email = ((CustomUser) auth.getPrincipal()).getDto().getEmail();
		
		map.put("email", email);
		
		List<InquiryDTO> list = service.list(map);
		
		model.addAttribute("list", list);
		model.addAttribute("email", email);
		model.addAttribute("currentPage", page);  //페이징
	    model.addAttribute("map", map);  //페이징

		return "mypage/inquiry/inquiry/view";
	}
	
	@PostMapping(value = "/delete.do")
	public String delete(Model model, String selectedInquiry) {

		int result = service.delete(selectedInquiry);

		if (result == 1) {

			return "redirect:/member/mypage/inquiry/inquiry/view.do";

		} else {
			
			System.out.println("inquiry delete error");
			
		}

		return "redirect:/member/mypage/inquiry/inquiry/view.do";
	}

}
