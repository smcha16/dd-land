package com.project.dd.mypage.voc.controller;

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
import com.project.dd.mypage.voc.domain.VOCDTO;
import com.project.dd.mypage.voc.service.MypageVOCService;

@Controller
@RequestMapping("/member/mypage/inquiry/voc")
public class MemberMypageVOCController {
	
	@Autowired
	private MypageVOCService service;
	
	/**
	 * 칭찬/불편/건의 내역을 조회하는 메서드입니다.
	 *
	 * @param model        데이터를 뷰에 전달하기 위한 Model 객체
	 * @param auth         현재 사용자의 인증 정보를 담은 Authentication 객체
	 * @param page         현재 페이지 번호
	 * @return             칭찬/불편/건의 내역을 보여주는 뷰 페이지
	 */
	@GetMapping(value = "/view.do")
	public String view(Model model, Authentication auth, @RequestParam(defaultValue = "1") int page) {
		
		Map<String, String> map = service.paging(page);  //페이징
		
		String email = ((CustomUser) auth.getPrincipal()).getDto().getEmail();
		
		map.put("email", email);
		
		List<VOCDTO> list = service.list(map);
		
		model.addAttribute("list", list);
		model.addAttribute("email", email);
		model.addAttribute("currentPage", page);  //페이징
	    model.addAttribute("map", map);  //페이징

		return "mypage/inquiry/voc/view";
	}
	
	/**
	 * 칭찬/불편/건의 내역을 삭제하는 메서드입니다.
	 *
	 * @param model            데이터를 뷰에 전달하기 위한 Model 객체
	 * @param selectedVOC      삭제할 칭찬/불편/건의 내역의 ID 배열
	 * @return                 삭제 후 칭찬/불편/건의 내역 조회 페이지로 리다이렉트
	 */
	@PostMapping(value = "/delete.do")
	public String delete(Model model, String[] selectedVOC) {

		int result = service.delete(selectedVOC);

		if (result == 1) {

			return "redirect:/member/mypage/inquiry/voc/view.do";

		} else {
			
			System.out.println("voc delete error");
			
		}

		return "redirect:/member/mypage/inquiry/voc/view.do";
	}


}
