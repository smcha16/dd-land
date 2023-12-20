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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.dd.login.domain.LoginDTO;
import com.project.dd.login.service.LoginService;
import com.project.dd.login.session.SessionConst;
import com.project.dd.register.domain.MemberDTO;
import com.project.dd.register.service.RegisterService;

import lombok.RequiredArgsConstructor;
/**
 *  UserLoginController 클래스입니다.
 *  회원 로그인 컨트롤러 입니다.
 * @author 김형우
 * 
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/user/login")
public class UserLoginController {
	
	
	private final LoginService loginService;
	private final RegisterService registerService;
	
	/**
	 * 로그인 화면을 불러옵니다.
	 * @return  로그인 화면 jsp를 리턴합니다.
	 * 
	 */
	@GetMapping("/view.do")
	public String loginForm() {

		return "user/login/view";
	}
	/**
	 *  로그인을 처리합니다.
	 * @param loginDTO 로그인 객체입니다.
	 * @param bindingResult 에러 체크 객체입니다.
	 * @param request 리퀘스트 객체입니다.
	 * @param model 모델 객체입니다.
	 * @return 메인 jsp 를 리턴합니다.
	 */
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
	/**
	 *  아이디 찾기페이지를 불러옵니다.
	 * @return
	 */
	@GetMapping("/findid.do")
	public String findIdForm(){
		
		return "user/login/findid";
	}
	/**
	 * 아이디 찾기를 처리합니다.
	 * @param memberDTO  회원 객체입니다.
	 * @param bindingResult  에러체크 객체입니다.
	 * @param mm  개월 수 입니다.
	 * @param dd  일수 입니다.
	 * @return
	 */
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
	/**
	 * 실패페이지입니다.
	 * @return fail jsp를 리턴합니다.
	 */
	@GetMapping("/fail.do")
	public String failForm() {
		
		return "user/login/fail";
	}	
	/**
	 * 아이디찾기,비밀번호찾기 성공 페이지입니다.
	 * @param user_seq 회원 시퀀스번호 입니다.
	 * @param model 모델 객체입니다.
	 * @return  success jsp 페이지를 리턴합니다.
	 */
	@GetMapping("/success.do")
	public String successForm(String user_seq,Model model) {
		
		MemberDTO findMemberDTO = loginService.findMember(user_seq);
		
		model.addAttribute("dto",findMemberDTO);
		return "user/login/success";
	}
	
	/**
	 * 비밀번호 찾기 화면을 불러옵니다.
	 * @param user_seq 회원 시퀀스 번호입니다.
	 * @param model 모델 객체입니다.
	 * @return 비번찾기 jsp 파일을 리턴합니다.
	 */
	@GetMapping("/findpw.do")
	public String findPwForm(String user_seq,Model model) {
		
		MemberDTO dto = loginService.findMember(user_seq);
		
		model.addAttribute("dto",dto);
		
		
		return "user/login/findpw";
	}
	/**
	 *  비번찾기를 처리합니다.
	 * @param pw 비밀번호입니다.
	 * @param user_seq  회원 시퀀스 번호입니다.
	 * @return  로그그인 페이지를 리턴합니다.
	 */
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
	/**
	 * 비밀번호 변경 페이지를 불러옵니다.
	 * @return changepw jsp 를 리턴합니다.
	 */
	@GetMapping("/changepw.do")
	public String changePwForm() {
		
		return "user/login/changepw";
		
	}
	/**
	 *  비밀번호 변경을 처리합니다.
	 * @param memberDTO  회원 객체 입니다.
	 * @param bindingResult 에러처리 객체입니다.
	 * @param model 모델 객체입니다.
	 * @param mm 개월수 입니다.
	 * @param dd  일 수 입니다.
	 * @return
	 */
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
