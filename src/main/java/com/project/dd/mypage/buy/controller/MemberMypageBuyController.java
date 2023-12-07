package com.project.dd.mypage.buy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.mypage.buy.domain.BuyDTO;
import com.project.dd.mypage.buy.service.MypageBuyService;

@Controller
@RequestMapping("/member/mypage/buy")
public class MemberMypageBuyController {
	
	@Autowired
	private MypageBuyService service;
	
	@GetMapping(value = "/view.do")
	public String view(Model model) {
		
		List<BuyDTO> list = service.list();
		
		model.addAttribute("list", list);

		return "mypage/buy/view";
	}

}
