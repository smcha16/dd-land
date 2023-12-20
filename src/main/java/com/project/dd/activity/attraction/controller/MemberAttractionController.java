package com.project.dd.activity.attraction.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.attraction.domain.BookUserDTO;
import com.project.dd.activity.attraction.service.AttractionService;
import com.project.dd.login.domain.CustomUser;

/**
 * 
 * @author 박나래
 * 
 * 어트랙션 예약을 담당하는 회원 전용 컨트롤러 클래스입니다.
 *
 */
@Controller
@RequestMapping(value = "/member/activity/attraction/reservation")
public class MemberAttractionController {

	@Autowired
	private AttractionService service;
	
	/**
	 * 
	 * 어트랙션을 예약할 수 있는 add 메서드입니다.
	 * 
	 * @param model 객체
	 * @param seq 어트랙션 번호
	 * @return jsp 파일명
	 */
	@GetMapping(value = "/add.do")
	public String add(Model model, String seq) {
		
		//해당 어트랙션 정보 가져오기
		AttractionDTO dto = service.getAttraction(seq);
		
		model.addAttribute("dto", dto);
		
		return "member/activity/attraction/reservation/add";
	}
	
	/**
	 * 
	 * 추가한 어트랙션 예약을 DB에서 처리하고 처리 결과에 따라 이동할 페이지를 호출하는 addok 메서드입니다.
	 * 
	 * @param model 객체
	 * @param dto 회원어트랙션예약 dto 객체
	 * @param auth 로그인한 사용자의 Authentication 객체
	 * @return 이동할 페이지 주소
	 */
	@PostMapping(value = "/addok.do")
	public String addok(Model model, BookUserDTO dto, Authentication auth) {

		dto.setUser_seq(((CustomUser)auth.getPrincipal()).getDto().getUser_seq());
		
		int result = service.addAttractionBook(dto);
		
		if (result > 0) {
			return "redirect:/user/activity/attraction/detail.do?seq=" + dto.getAttraction_seq();
		} else {
			return "redirect:/member/activity/attraction/add.do";
		}
		
	}
	
}
