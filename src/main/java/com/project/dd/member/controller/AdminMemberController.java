package com.project.dd.member.controller;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.communication.faq.domain.FaqDTO;
import com.project.dd.login.service.LoginService;
import com.project.dd.member.service.AdminMemberService;
import com.project.dd.register.domain.MemberDTO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/member")
public class AdminMemberController {

	private final AdminMemberService service;
	private final LoginService loginService;

	@GetMapping("/view.do")
	public String viewForm(Model model,
							@RequestParam(defaultValue = "1") int page) {
	
		String type = "없음";
		
		Map<String, String> map = service.paging(type, page);		
	
		List<MemberDTO> list = service.getMemberList(map);
		
		// 페이징 계산 로직 추가
	    int totalPages = Integer.parseInt(map.get("totalPages"));
	    int startPage = 1;
	    int endPage = Math.min(totalPages, 10);

	    if (page > 10) {
	        startPage = Math.max(1, (page - 1) / 10 * 10 + 1);
	        endPage = Math.min(totalPages, startPage + 9);
	    }
	    
	    
	
		model.addAttribute("currentPage", page);
		model.addAttribute("map",map);
		model.addAttribute("list", list);
		 model.addAttribute("startPage", startPage);
		    model.addAttribute("endPage", endPage);
		    
		return "admin/member/view";
	}

	@PostMapping("/view.do")
	public String view(String query, Model model) {

		List<MemberDTO> list = service.search(query);

		model.addAttribute("list", list);

		return "admin/member/search";

	}

}
