package com.project.dd.mypage.attraction.controller;

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
import com.project.dd.mypage.attraction.domain.AttractionDTO;
import com.project.dd.mypage.attraction.service.MypageAttractionService;

/**
 * 회원 마이페이지 어트랙션 예약내역을 관리하는 컨트롤러입니다.
 * 회원의 어트랙션 예약목록을 조회하고, 페이징하여 보여주며, 예약을 취소하는 기능을 제공합니다.
 */
@Controller
@RequestMapping("/member/mypage/attraction")
public class MemberMypageAttractionController {

	@Autowired
	private MypageAttractionService service;

	/**
     * 회원의 어트랙션 예약을 조회하여 페이지로 이동합니다.
     *
     * @param model      Model 객체
     * @param auth       Authentication 객체
     * @param page       페이지 번호 (기본값: 1)
     * @return           관심 목록을 보여주는 view 이름
     */
	@GetMapping(value = "/view.do")
	public String view(Model model, Authentication auth, @RequestParam(defaultValue = "1") int page) {

		String email = ((CustomUser) auth.getPrincipal()).getDto().getEmail();
		
		Map<String, String> map = service.paging(page, email);  //페이징
		
		map.put("email", email);
		
		List<AttractionDTO> list = service.list(map);

		model.addAttribute("list", list);
		model.addAttribute("email", email);
		model.addAttribute("currentPage", page);  //페이징
	    model.addAttribute("map", map);  //페이징

		return "mypage/attraction/view";
	}
	
	/**
     * 회원의 이전 어트랙션 예약을 조회하여 페이지로 이동합니다.
     *
     * @param model      Model 객체
     * @param auth       Authentication 객체
     * @param page       페이지 번호 (기본값: 1)
     * @return           다른 관심 목록을 보여주는 view 이름
     */
	@GetMapping(value = "/pview.do")
	public String pview(Model model, Authentication auth, @RequestParam(defaultValue = "1") int page) {

		String email = ((CustomUser) auth.getPrincipal()).getDto().getEmail();
		
		Map<String, String> map = service.pPaging(page, email);  //페이징
		
		map.put("email", email);
		
		List<AttractionDTO> plist = service.plist(map);

		model.addAttribute("plist", plist);
		model.addAttribute("email", email);
		model.addAttribute("currentPage", page);  //페이징
	    model.addAttribute("map", map);  //페이징

		return "mypage/attraction/pview";
	}
	
	/**
     * 선택한 어트랙션 예약을 삭제합니다.
     *
     * @param model                Model 객체
     * @param selectedAttraction   선택된 관심 목록 배열
     * @return                     삭제 후 이동할 URL
     */
	@PostMapping(value = "/delete.do")
	public String delete(Model model, String[] selectedAttraction) {

		int result = service.delete(selectedAttraction);

		if (result == 1) {

			return "redirect:/member/mypage/attraction/view.do";

		} else {
			
			System.out.println("attraction delete error");
			
		}

		return "redirect:/member/mypage/attraction/view.do";
	}

}
