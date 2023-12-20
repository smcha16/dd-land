package com.project.dd.member.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.login.service.LoginService;
import com.project.dd.member.service.AdminMemberService;
import com.project.dd.register.domain.MemberDTO;

import lombok.RequiredArgsConstructor;

/**
 * 관리자가 회원 정보를 관리하는 컨트롤러 클래스
 * 
 * @author green
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/member")
public class AdminMemberController {

	private final AdminMemberService service;
	private final LoginService loginService;

	/**
	 * 회원 목록 조회 페이지를 보여주는 메서드
	 * 
	 * @param model Model 객체
	 * @param page  현재 페이지 번호
	 * @return 회원 목록 조회 페이지 뷰 이름
	 */
	@GetMapping("/view.do")
	public String viewForm(Model model, @RequestParam(defaultValue = "1") int page) {
		// 페이징 정보 및 회원 목록을 가져오는 서비스 호출
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

		// 모델에 속성 추가
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("list", list);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		// 회원 목록 조회 페이지로 이동
		return "admin/member/view";
	}

	/**
	 * 회원 목록에서 검색을 수행하는 메서드
	 * 
	 * @param query 검색어
	 * @param model Model 객체
	 * @return 검색 결과를 보여주는 페이지 뷰 이름
	 */
	@PostMapping("/view.do")
	public String view(String query, Model model) {
		// 검색 서비스 호출
		List<MemberDTO> list = service.search(query);

		// 모델에 검색 결과 추가
		model.addAttribute("list", list);

		// 검색 결과를 보여주는 페이지로 이동
		return "admin/member/search";
	}

	/**
	 * 회원을 삭제하는 메서드
	 * 
	 * @param user_seq 삭제할 회원의 일련번호
	 * @return 회원 목록 페이지로 리다이렉트
	 */
	@PostMapping("/del.do")
	public String del(String user_seq) {
		// 회원 삭제 서비스 호출
		int result = service.del(user_seq);

		// 회원 목록 페이지로 리다이렉트
		return "redirect:/admin/member/view.do";
	}

	/**
	 * 회원 정보 수정 폼을 보여주는 메서드
	 * 
	 * @param seq   수정할 회원의 일련번호
	 * @param model Model 객체
	 * @return 회원 정보 수정 폼 뷰 이름
	 */
	@GetMapping("/edit.do")
	public String editForm(@RequestParam(name = "seq") String seq, Model model) {
		// 회원 정보 조회 서비스 호출
		MemberDTO dto = loginService.findMember(seq);

		// 모델에 회원 정보 추가
		model.addAttribute("dto", dto);

		// 회원 정보 수정 폼으로 이동
		return "admin/member/edit";
	}

	/**
	 * 회원 정보를 수정하는 메서드
	 * 
	 * @param memberDTO 수정된 회원 정보
	 * @param 에러객체 입니다
	 * @param 회원 시퀀스 번호입니다.
	 * @param 모델 객체입니다.
	 * @return 회원 목록 페이지로 리다이렉트
	 */
	@PostMapping("/edit.do")
	public String edit(@Valid MemberDTO memberDTO,BindingResult bindingResult,String user_seq,Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("dto", memberDTO);
			return "admin/member/edit";
		}
		System.out.println(user_seq);
		memberDTO.setUser_seq(user_seq);
		System.out.println(memberDTO.toString());
		// 회원 정보 수정 서비스 호출
		int result = service.edit(memberDTO);

		// 회원 목록 페이지로 리다이렉트
		return "redirect:/admin/member/view.do";
	}
}
