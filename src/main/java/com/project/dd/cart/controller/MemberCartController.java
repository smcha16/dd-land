package com.project.dd.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.dd.cart.domain.CartDTO;
import com.project.dd.cart.service.CartService;

@Controller
public class MemberCartController {

	@Autowired
	private CartService service;
	
	@GetMapping(value = "/member/cart/view.do")
	public String view(Model model, String user_seq) {
		List<CartDTO> list = service.getUserList(user_seq);
		
		model.addAttribute("list", list);
		
		return "member/cart/view";
	}
	
}
