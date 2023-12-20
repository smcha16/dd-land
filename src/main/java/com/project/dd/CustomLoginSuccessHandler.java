package com.project.dd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.project.dd.login.session.SessionConst;

/**
 * 사용자 정의 인증 성공 핸들러 클래스입니다.
 * @author pega0
 *
 */
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	/**
     * 인증이 성공했을 때 호출되는 메서드입니다.
     *
     * @param request        HTTP 요청 객체
     * @param response       HTTP 응답 객체
     * @param authentication 인증 객체
     * @throws IOException      입출력 예외가 발생할 경우
     * @throws ServletException 서블릿 예외가 발생할 경우
     */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

		List<String> roleNames = new ArrayList<String>();
		
		authentication.getAuthorities().forEach(authirity -> {
			
			roleNames.add(authirity.getAuthority());
			
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
