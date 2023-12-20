package com.project.dd.mypage.buy.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.login.domain.CustomUser;
import com.project.dd.mypage.buy.domain.BuyDTO;
import com.project.dd.mypage.buy.service.MypageBuyService;

@Controller
@RequestMapping("/member/mypage/buy")
public class MemberMypageBuyController {
	
	@Autowired
	private MypageBuyService service;
	
	/**
	 * 회원의 구매 내역을 보여주는 메서드입니다.
	 *
	 * @param model       화면에 전달될 데이터를 담은 Model 객체
	 * @param auth        현재 사용자의 인증 정보를 포함한 Authentication 객체
	 * @param page        현재 페이지 번호 (기본값: 1)
	 * @return            구매 내역을 보여주는 View 페이지
	 */
	@GetMapping(value = "/view.do")
	public String view(Model model, Authentication auth, @RequestParam(defaultValue = "1") int page) {
		
		String email = ((CustomUser) auth.getPrincipal()).getDto().getEmail();
		
		Map<String, String> map = service.paging(page, email);  //페이징
		
		map.put("email", email);
		
		List<BuyDTO> list = service.list(map);
		
		model.addAttribute("list", list);
		model.addAttribute("email", email);
		model.addAttribute("currentPage", page);  //페이징
	    model.addAttribute("map", map);  //페이징

		return "mypage/buy/view";
	}
	
	/**
	 * 회원의 이전 구매 내역을 보여주는 메서드입니다.
	 *
	 * @param model       화면에 전달될 데이터를 담은 Model 객체
	 * @param auth        현재 사용자의 인증 정보를 포함한 Authentication 객체
	 * @param page        현재 페이지 번호 (기본값: 1)
	 * @return            페이징된 구매 내역을 보여주는 View 페이지
	 */
	@GetMapping(value = "/pview.do")
	public String pview(Model model, Authentication auth, @RequestParam(defaultValue = "1") int page) {
		
		String email = ((CustomUser) auth.getPrincipal()).getDto().getEmail();
		
		Map<String, String> map = service.pPaging(page, email);  //페이징
		
		map.put("email", email);
		
		List<BuyDTO> plist = service.plist(map);
		
		model.addAttribute("plist", plist);
		model.addAttribute("email", email);
		model.addAttribute("currentPage", page);  //페이징
	    model.addAttribute("map", map);  //페이징

		return "mypage/buy/pview";
	}
	
	/**
	 * 선택된 구매 내역을 삭제하는 메서드입니다.
	 *
	 * @param model           화면에 전달될 데이터를 담은 Model 객체
	 * @param selectedItem    삭제할 구매 항목 배열
	 * @return                구매 내역 삭제 후 리다이렉트하는 View 페이지
	 */
	@PostMapping(value = "/delete.do")
	public String delete(Model model, String[] selectedItem) {
		
		int result = service.delete(selectedItem);

		if (result == 1) {

			return "redirect:/member/mypage/buy/view.do";

		} else {
			
			System.out.println("buy delete error");
			
		}

		return "redirect:/member/mypage/buy/view.do";

	}

}
