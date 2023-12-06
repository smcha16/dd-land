package com.project.dd.mypage.ticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.mypage.ticket.domain.TicketDTO;
import com.project.dd.mypage.ticket.mapper.MypageTicketMapper;

@Controller
@RequestMapping("/member/mypage/ticket")
public class MemberMypageTicketController {
	
	@Autowired
	private MypageTicketMapper mapper;
	
	@GetMapping(value = "/view.do")
	public String view(Model model) {
		
		List<TicketDTO> list = mapper.list();
		
		model.addAttribute("list", list);

		return "mypage/ticket/view";
	}

}
