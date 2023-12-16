	package com.project.dd.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.CustomLoginSuccessHandler;
import com.project.dd.CustomUserDetailsService;
import com.project.dd.login.domain.LoginDTO;
import com.project.dd.login.service.LoginService;
import com.project.dd.login.session.SessionConst;
import com.project.dd.register.domain.MemberDTO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/login")
public class UserLoginController {
	
	
	private final LoginService loginService;
	
	
	@GetMapping("/view.do")
	public String loginForm() {

		return "user/login/view";
	}
	
	@PostMapping("/view.do")
	public String login(@Valid LoginDTO loginDTO,BindingResult bindingResult,HttpServletRequest request,Model model ) {
		if (bindingResult.hasErrors()) {
			return "user/login/view";
		}
		
	MemberDTO loginMember = loginService.login(loginDTO.getEmail(),loginDTO.getPw());
		
	if (loginMember == null) {
		bindingResult.reject("loginFail","아이디 또는 비밀번호가 틀렸습니다.");
		return "user/login/view";
	}
	
	HttpSession session = request.getSession();
	
	session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
	
	
		
		return "redirect:main";
	}
	
	@GetMapping("/findId.do")
	public String findId(){
		
		return "user/login/findId";
	}
	
	
}
