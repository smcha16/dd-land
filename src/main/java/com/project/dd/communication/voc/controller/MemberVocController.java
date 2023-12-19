package com.project.dd.communication.voc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.communication.voc.domain.VocDTO;
import com.project.dd.communication.voc.service.VocService;
import com.project.dd.login.domain.CustomUser;

/**
 * 회원용 칭찬/불편/건의 컨트롤러 클래스입니다.
 * 
 * @author sumin
 */
@Controller
@RequestMapping("/member/communication/voc")
public class MemberVocController {
	
	@Autowired
	private VocService service;
	
	/**
	 * 칭찬/불편/건의를 추가하는 폼을 보여주는 메서드입니다.
	 *
	 * @param auth 인증 정보를 담은 Authentication 객체
	 * @param model Spring의 Model 객체
	 * @return 칭찬/불편/건의 추가 폼을 보여주는 뷰의 경로
	 */
	@GetMapping(value = "/add.do")
	public String add(Authentication auth, Model model) {

		String name = ((CustomUser)auth.getPrincipal()).getDto().getName();
		String email = ((CustomUser)auth.getPrincipal()).getDto().getEmail();
		String tel = ((CustomUser)auth.getPrincipal()).getDto().getTel();
		
		List<String> list = service.getVisitDateList(email);

		model.addAttribute("name", name);
		model.addAttribute("email", email);
		model.addAttribute("tel", tel);
		model.addAttribute("list", list);

		return "member/communication/voc/add";

	}
	
	/**
	 * 칭찬/불편/건의를 추가하는 메서드입니다.
	 *
	 * @param auth 인증 정보를 담은 Authentication 객체
	 * @param dto 추가할 칭찬/불편/건의의 DTO 객체
	 * @param req HttpServletRequest 객체
	 * @param doc 첨부 파일
	 * @return 칭찬/불편/건의 목록을 보여주는 뷰로의 리다이렉트 경로 또는 칭찬/불편/건의 추가 폼의 뷰로의 리다이렉트 경로
	 */
	@PostMapping(value = "/addok.do")
	public String addOk(Authentication auth, VocDTO dto, HttpServletRequest req, MultipartFile doc) {
		
		VocDTO voc = service.addFile(dto, req, doc);

		String user_seq = ((CustomUser)auth.getPrincipal()).getDto().getUser_seq();
		
		dto.setUser_seq(user_seq);
	
		int result = service.addVoc(voc);
	
	 	if (result == 1) {

	 		return "redirect:/index.do";
	 
	 	} else {
	 
	 		return "redirect:/member/communication/voc/add.do";
	 
	 	}
	 
	}

}
