package com.project.dd.mypage.ticket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/mypage/ticket")
public class MemberMypageTicketController {
	
	@GetMapping(value = "/view.do")
	public String view(Model model) {

		return "mypage/ticket/view";
	}

}
