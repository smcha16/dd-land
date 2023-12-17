package com.project.dd.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.login.domain.LoginDTO;
import com.project.dd.login.service.LoginService;
import com.project.dd.login.session.SessionConst;
import com.project.dd.register.domain.MemberDTO;
import com.project.dd.register.service.RegisterService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/login")
public class UserLoginController {
	
	
	private final LoginService loginService;
	private final RegisterService registerService;
	
	
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
	
	@GetMapping("/findid.do")
	public String findIdForm(){
		
		return "user/login/findid";
	}
	
	@PostMapping("/findid.do")
	public String findId(@Valid MemberDTO memberDTO,BindingResult bindingResult,
			@RequestParam(name="mm")String mm,@RequestParam(name="dd")String dd ) {
		if (bindingResult.hasErrors()) {
			return "user/login/findid";
		}
		
		String birthday = registerService.dayChange(memberDTO, mm, dd);
		
		String formattedPhoneNumber1 = registerService.formatPhoneNumber(memberDTO.getTel());
		
		memberDTO.setBirth(birthday);
		memberDTO.setTel(formattedPhoneNumber1);
		
		System.out.println(memberDTO.toString());
		
		MemberDTO findMember = loginService.findId(memberDTO);
		
		
		if (findMember == null) {
			return"user/login/fail";
		}
		
		String user_seq = findMember.getUser_seq();
		
		return "redirect:/user/login/success.do?user_seq="+user_seq;
	}
	
	@GetMapping("/fail.do")
	public String failForm() {
		
		return "user/login/fail";
	}	
	
	@GetMapping("/success.do")
	public String successForm(String user_seq,Model model) {
		
		MemberDTO findMemberDTO = loginService.findMember(user_seq);
		
		model.addAttribute("dto",findMemberDTO);
		return "user/login/success";
	}
	
	
	@GetMapping("/findpw.do")
	public String findPwForm(String user_seq,Model model) {
		
		MemberDTO dto = loginService.findMember(user_seq);
		
		model.addAttribute("dto",dto);
		
		
		return "user/login/findpw";
	}
	@PostMapping("/findpw.do")
	public String findPw(String pw,String user_seq) {
		
		System.out.println(pw);
		System.out.println(user_seq);
		
		int result = loginService.changePw(pw,user_seq);
		
		MemberDTO memberDTO = loginService.findMember(user_seq);
		
		
		// 비밀번호 변경후 암호
		loginService.passwordEncoder(memberDTO);
		
		System.out.println(memberDTO.getPw());
		
		return"redirect:/user/login/view.do";
	}
	@GetMapping("/changepw.do")
	public String changePwForm() {
		
		return "user/login/changepw";
		
	}
	
	@PostMapping("/changepw.do")
	public String changePw(@Valid MemberDTO memberDTO,BindingResult bindingResult,Model model,
			@RequestParam(name="mm")String mm,@RequestParam(name="dd")String dd ) {
		if (bindingResult.hasErrors()) {
			return"user/login/changepw";
		}
		String birthday = registerService.dayChange(memberDTO, mm, dd);
		
		memberDTO.setBirth(birthday);
		String user_seq = loginService.findSeq(memberDTO);
		memberDTO.setUser_seq(user_seq);
		System.out.println(memberDTO.toString());
		

		
		

		model.addAttribute("dto",memberDTO);
		return "user/login/findpw";
		
	}
	
}
