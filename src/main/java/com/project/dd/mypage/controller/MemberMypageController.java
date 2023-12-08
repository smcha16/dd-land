package com.project.dd.mypage.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.dd.login.domain.CustomUser;
import com.project.dd.mypage.modify.domain.ModifyDTO;


@Controller
public class MemberMypageController {

	@GetMapping(value = "/member/mypage/view.do")
	public String name(Model model, Authentication auth) {
		
		String name = ((CustomUser)auth.getPrincipal()).getDto().getName();
		
		model.addAttribute("name", name);
		
		return "mypage";
	}
}
