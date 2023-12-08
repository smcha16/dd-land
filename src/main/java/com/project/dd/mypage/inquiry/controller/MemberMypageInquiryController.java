package com.project.dd.mypage.inquiry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.mypage.inquiry.domain.InquiryDTO;
import com.project.dd.mypage.inquiry.service.MypageInquiryService;

@Controller
@RequestMapping("/member/mypage/inquiry/inquiry")
public class MemberMypageInquiryController {
	
	@Autowired
	private MypageInquiryService service;
	
	@GetMapping(value = "/view.do")
	public String view(Model model) {
		
		List<InquiryDTO> list = service.list();
		
		model.addAttribute("list", list);

		return "mypage/inquiry/inquiry/view";
	}

}
