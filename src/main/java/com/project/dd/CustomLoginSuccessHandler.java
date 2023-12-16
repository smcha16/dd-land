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

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

		List<String> roleNames = new ArrayList<String>();
		
		authentication.getAuthorities().forEach(authirity -> {
			
			roleNames.add(authirity.getAuthority());
			
		});
		
		if (roleNames.contains("2")) {
			
			response.sendRedirect("/dd/admin.do");
			
		} else if (roleNames.contains("1")) {
			
			
			response.sendRedirect("/dd/index.do");
			
		} else {
			
			
			response.sendRedirect("/dd/index.do");
			
		}
		
	}

}
