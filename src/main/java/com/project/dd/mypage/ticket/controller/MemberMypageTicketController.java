package com.project.dd.mypage.ticket.controller;

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
import com.project.dd.mypage.ticket.domain.TicketDTO;
import com.project.dd.mypage.ticket.service.MypageTicketService;

@Controller
@RequestMapping("/member/mypage/ticket")
public class MemberMypageTicketController {

	@Autowired
	private MypageTicketService service;

	/**
	 * 회원의 티켓 예매 내역을 조회하는 메서드입니다.
	 *
	 * @param model        데이터를 뷰에 전달하기 위한 Model 객체
	 * @param dto          티켓 예매 정보를 담은 DTO 객체
	 * @param auth         현재 사용자의 인증 정보를 담은 Authentication 객체
	 * @param page         현재 페이지 번호
	 * @return             회원의 티켓 예매 내역을 보여주는 뷰 페이지
	 */
	@GetMapping(value = "/view.do")
	public String view(Model model, TicketDTO dto, Authentication auth, @RequestParam(defaultValue = "1") int page) {

		String email = ((CustomUser) auth.getPrincipal()).getDto().getEmail();
		
		Map<String, String> map = service.paging(page, email);  //페이징
		
		map.put("email", email);
		map.put("user_book_seq", dto.getUser_book_seq());

		List<TicketDTO> list = service.list(map);

		
		model.addAttribute("list", list);
		model.addAttribute("email", email);
		model.addAttribute("currentPage", page);  //페이징
	    model.addAttribute("map", map);  //페이징

		return "mypage/ticket/view";
	}
	
	/**
	 * 티켓 예매 내역의 페이징 처리된 뷰를 반환하는 메서드입니다.
	 *
	 * @param model        데이터를 뷰에 전달하기 위한 Model 객체
	 * @param dto          티켓 예매 정보를 담은 DTO 객체
	 * @param auth         현재 사용자의 인증 정보를 담은 Authentication 객체
	 * @param page         현재 페이지 번호
	 * @return             페이징 처리된 티켓 예매 내역을 보여주는 뷰 페이지
	 */
	@GetMapping(value = "/pview.do")
	public String pview(Model model, TicketDTO dto, Authentication auth, @RequestParam(defaultValue = "1") int page) {

		String email = ((CustomUser) auth.getPrincipal()).getDto().getEmail();
		
		Map<String, String> map = service.pPaging(page, email);  //페이징
		
		map.put("email", email);
		map.put("user_book_seq", dto.getUser_book_seq());

		List<TicketDTO> plist = service.plist(map);

		
		model.addAttribute("plist", plist);
		model.addAttribute("email", email);
		model.addAttribute("currentPage", page);  //페이징
	    model.addAttribute("map", map);  //페이징

		return "mypage/ticket/pview";
	}
	

	/**
	 * 선택한 티켓을 삭제하는 메서드입니다.
	 *
	 * @param model              데이터를 뷰에 전달하기 위한 Model 객체
	 * @param selectedTickets    삭제할 티켓의 ID 목록
	 * @return                   티켓 삭제 후 목록을 보여주는 페이지로 리다이렉트
	 */
	@PostMapping(value = "/delete.do")
	public String delete(Model model, String[] selectedTickets) {

		int result = service.delete(selectedTickets);

		if (result == 1) {

			return "redirect:/member/mypage/ticket/view.do";

		} else {
			
			System.out.println("ticket delete error");
			
		}

		return "redirect:/member/mypage/ticket/view.do";
	}
	
}
