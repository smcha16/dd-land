package com.project.dd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

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
