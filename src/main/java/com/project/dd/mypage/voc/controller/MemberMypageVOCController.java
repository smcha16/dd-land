package com.project.dd.mypage.voc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.mypage.voc.domain.VOCDTO;
import com.project.dd.mypage.voc.service.MypageVOCService;

@Controller
@RequestMapping("/member/mypage/inquiry/voc")
public class MemberMypageVOCController {
	
	@Autowired
	private MypageVOCService service;
	
	@GetMapping(value = "/view.do")
	public String view(Model model) {
		
		List<VOCDTO> list = service.list();
		
		model.addAttribute("list", list);

		return "mypage/inquiry/voc/view";
	}

}
