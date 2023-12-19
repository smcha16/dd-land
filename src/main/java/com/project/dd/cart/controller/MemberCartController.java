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

@Controller
public class MemberCartController {

	@Autowired
	private CartService service;
	
	@GetMapping(value = "/member/cart/view.do")
	public String view(Model model, Authentication auth) {
		String user_seq = ((CustomUser)auth.getPrincipal()).getDto().getUser_seq();
		
		List<CartDTO> list = service.getUserList(user_seq);
		
		model.addAttribute("list", list);
		
		return "member/cart/view";
	}
	
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
