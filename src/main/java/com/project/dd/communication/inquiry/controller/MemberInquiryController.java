package com.project.dd.communication.inquiry.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.communication.inquiry.domain.InquiryDTO;
import com.project.dd.communication.inquiry.service.InquiryService;
import com.project.dd.login.domain.CustomUser;

/**
 * 회원용 이용문의 컨트롤러 클래스입니다.
 * 
 * @author sumin
 */
@Controller
@RequestMapping("/member/communication/inquiry")
public class MemberInquiryController {
	
	@Autowired
	private InquiryService service;
	
	/**
	 * 이용문의를 추가하는 폼을 보여주는 메서드입니다.
	 *
	 * @param auth 인증 정보를 담은 Authentication 객체
	 * @param model Spring의 Model 객체
	 * @return 이용문의 추가 폼을 보여주는 뷰의 경로
	 */
	@GetMapping(value = "/add.do")
	public String add(Authentication auth, Model model) {

		String name = ((CustomUser)auth.getPrincipal()).getDto().getName();
		String email = ((CustomUser)auth.getPrincipal()).getDto().getEmail();
		
		model.addAttribute("name", name);
		model.addAttribute("email", email);

		return "member/communication/inquiry/add";

	}
	
	/**
	 * 이용문의를 추가하는 메서드입니다.
	 *
	 * @param auth 인증 정보를 담은 Authentication 객체
	 * @param dto 추가할 이용문의의 DTO 객체
	 * @param req HttpServletRequest 객체
	 * @param doc 첨부 파일
	 * @return 이용문의 목록을 보여주는 뷰로의 리다이렉트 경로 또는 이용문의 추가 폼의 뷰로의 리다이렉트 경로
	 */
	@PostMapping(value = "/addok.do")
	public String addOk(Authentication auth, InquiryDTO dto, HttpServletRequest req, MultipartFile doc) {
		
		InquiryDTO inquiry = service.addFile(dto, req, doc);
		
		String user_seq = ((CustomUser)auth.getPrincipal()).getDto().getUser_seq();
		
		dto.setUser_seq(user_seq);

		int result = service.addInquiry(inquiry);
		
		if (result == 1) {

	 		return "redirect:/index.do";
	 
	 	} else {
	 
	 		return "redirect:/member/communication/inquiry/add.do";
	 
	 	}
	 
	}

}
