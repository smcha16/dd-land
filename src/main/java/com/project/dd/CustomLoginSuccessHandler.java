package com.project.dd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


/**
<<<<<<< HEAD
 * 사용자 정의 로그인 성공 핸들러.
 * 작성자: 김형우
 */
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
	
	/**
	 * 로그인 성공 시 호출되는 메서드.
	 * @param request HttpServletRequest 객체
	 * @param response HttpServletResponse 객체
	 * @param authentication 인증 객체
	 */

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

		List<String> roleNames = new ArrayList<String>();
		
		authentication.getAuthorities().forEach(authority -> {
			roleNames.add(authority.getAuthority());
		});
		
		if (roleNames.contains("ROLE_2")) {
			response.sendRedirect("/dd/admin/index.do");
		} else if (roleNames.contains("ROLE_1")) {
			response.sendRedirect("/dd/index.do");
		} else {
			response.sendRedirect("/dd/index.do");
		}
		
		
	}

}
