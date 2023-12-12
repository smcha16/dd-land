package com.project.dd.communication.voc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.communication.voc.domain.VocDTO;
import com.project.dd.communication.voc.service.VocService;
import com.project.dd.login.domain.CustomUser;

@Controller
@RequestMapping("/member/communication/voc")
public class MemberVocController {
	
	@Autowired
	private VocService service;
	
	@GetMapping(value = "/add.do")
	public String add(Authentication auth, Model model) {

		String name = ((CustomUser)auth.getPrincipal()).getDto().getName();
		String email = ((CustomUser)auth.getPrincipal()).getDto().getEmail();
		String tel = ((CustomUser)auth.getPrincipal()).getDto().getTel();
		
		List<String> list = service.getVisitDateList(email);

		model.addAttribute("name", name);
		model.addAttribute("email", email);
		model.addAttribute("tel", tel);
		model.addAttribute("list", list);

		return "member/communication/voc/add";

	}
	
	@PostMapping(value = "/addok.do")
	public String addOk(Authentication auth, VocDTO dto, HttpServletRequest req, MultipartFile doc) {
		
		VocDTO voc = service.addFile(dto, req, doc);

		String user_seq = ((CustomUser)auth.getPrincipal()).getDto().getUser_seq();
		
		dto.setUser_seq(user_seq);
	
		int result = service.addVoc(voc);
	
	 	if (result == 1) {

	 		return "redirect:/index.do";
	 
	 	} else {
	 
	 		return "redirect:/member/communication/voc/add.do";
	 
	 	}
	 
	}

}
