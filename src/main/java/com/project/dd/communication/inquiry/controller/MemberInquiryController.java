package com.project.dd.communication.inquiry.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.communication.inquiry.domain.InquiryDTO;
import com.project.dd.communication.inquiry.service.InquiryService;
import com.project.dd.login.domain.CustomUser;

@Controller
@RequestMapping("/member/communication/inquiry")
public class MemberInquiryController {
	
	@Autowired
	private InquiryService service;
	
	@GetMapping(value = "/add.do")
	public String add(Authentication auth, Model model) {

		String name = ((CustomUser)auth.getPrincipal()).getDto().getName();
		String email = ((CustomUser)auth.getPrincipal()).getDto().getEmail();
		
		model.addAttribute("name", name);
		model.addAttribute("email", email);

		return "member/communication/inquiry/add";

	}
	
	@PostMapping(value = "/addok.do")
	public String addOk(Authentication auth, InquiryDTO dto, HttpServletRequest req, MultipartFile doc) {
		
		if (doc != null && !doc.isEmpty()) {
			
			dto.setAttach(service.saveFile(req, doc));
			
		} else {
			
			dto.setAttach(null);
			
		}

		String user_seq = ((CustomUser)auth.getPrincipal()).getDto().getUser_seq();
		
		dto.setUser_seq(user_seq);
	
		int result = service.addInquiry(dto);
	
	 	if (result == 1) {

	 		return "redirect:/index.do";
	 
	 	} else {
	 
	 		return "redirect:/member/communication/inquiry/add.do";
	 
	 	}
	 
	}

}
