package com.project.dd.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 권한 관련 컨트롤러 클래스입니다.
 * @author pega0
 *
 */
@Controller
public class AuthController {

	/**
     * 권한 접근 오류 페이지를 반환합니다.
     *
     * @param model Spring MVC 모델
     * @return 권한 접근 오류 페이지 뷰 이름
     */
	@GetMapping(value = "/auth/accesserror.do")
	public String auth(Model model) {
		
		return "auth/accesserror";
	}
	
	/*
	@GetMapping(value = "/auth/mylogout.do")
	public String mylogout(Model model) {

		return "auth/mylogout";
	}

	@GetMapping(value = "/auth/myinfo.do")
	public String myinfo(Model model) {

		return "auth/myinfo";
	}
	*/
	
}