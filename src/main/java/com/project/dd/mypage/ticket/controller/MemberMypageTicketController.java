package com.project.dd.mypage.ticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.login.domain.CustomUser;
import com.project.dd.mypage.ticket.domain.TicketDTO;
import com.project.dd.mypage.ticket.service.MypageTicketService;

@Controller
@RequestMapping("/member/mypage/ticket")
public class MemberMypageTicketController {
	
	@Autowired
	private MypageTicketService service;
	
	@GetMapping(value = "/view.do")
	public String view(Model model, Authentication auth) {
		
		String email = ((CustomUser)auth.getPrincipal()).getDto().getEmail();
		
		List<TicketDTO> list = service.list(email);
		
		model.addAttribute("list", list);
		model.addAttribute("email", email);
		
		return "mypage/ticket/view";
	}
	
	@PostMapping(value = "/delete.do")
	public String name(Model model, String selectedTickets, Authentication auth) {
		
		//System.out.println(selectedTickets);
		
		service.delete(selectedTickets);
		
		return "redirect:/dd/member/mypage/ticket/view.do";
	}
	
	

}
