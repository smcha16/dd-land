package com.project.dd.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.dd.cart.domain.CartDTO;
import com.project.dd.cart.service.CartService;
import com.project.dd.login.domain.CustomUser;

/**
 * 회원 장바구니 관련 컨트롤러 클래스입니다.
 * @author pega0
 *
 */
@Controller
public class MemberCartController {

	@Autowired
	private CartService service;
	
	/**
     * 회원의 장바구니 목록 페이지를 반환합니다.
     *
     * @param model Spring MVC 모델
     * @param auth 현재 사용자의 인증 객체
     * @return 장바구니 목록 페이지 뷰 이름
     */
	@GetMapping(value = "/member/cart/view.do")
	public String view(Model model, Authentication auth) {
		String user_seq = ((CustomUser)auth.getPrincipal()).getDto().getUser_seq();
		
		List<CartDTO> list = service.getUserList(user_seq);
		
		model.addAttribute("list", list);
		
		return "member/cart/view";
	}
	
	/**
     * 선택한 장바구니 항목을 삭제하고, 장바구니 목록 페이지로 리다이렉트합니다.
     *
     * @param model Spring MVC 모델
     * @param cart_seq 삭제할 장바구니 항목의 일련번호 배열
     * @return 장바구니 목록 페이지로의 리다이렉트 경로
     */
	@PostMapping(value = "/member/cart/del.do")
	public String del(Model model, String[] cart_seq) {

		int result = service.delCart(cart_seq);
		
		if (result > 0) {
			return "redirect:/member/cart/view.do";
		} else {
			return "redirect:/member/cart/view.do";
		}
	}
	
}
