package com.project.dd.mypage.modify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.login.domain.CustomUser;
import com.project.dd.mypage.modify.domain.ModifyDTO;
import com.project.dd.mypage.modify.service.MypageModifyService;

@Controller
@RequestMapping("/member/mypage/modify")
public class MemberMypageModifyController {
	
	@Autowired
	private MypageModifyService service;
	
	@GetMapping(value = "/view.do")
	public String view(Model model, Authentication auth) {
		
		String email = ((CustomUser) auth.getPrincipal()).getDto().getEmail();
		
		List<ModifyDTO> list = service.list(email);
		
		System.out.println(list.toString());
		
		model.addAttribute("list", list);

		return "mypage/modify/view";
	}
	
	@PostMapping(value = "/edit.do")
	public String edit(Model model, ModifyDTO dto) {
		
		 int result = service.edit(dto);
		 
			  if (result == 1) {
			  
			  return "redirect:/member/mypage/view.do";
			  
			  } else {
			  
			  System.out.println("edit error");
			  
			  }
			 

		 return "redirect:/member/mypage/modify/view.do";
	}

}
