package com.project.dd;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping(value = "/index.do")
	public String index(Model model) {

		return "main";
	}
	
	@GetMapping(value = "/admin.do")
	public String admin(Model model) {

		return "admin";
	}
	
	@GetMapping(value = "/mypage.do")
	public String mypage(Model model) {

		return "mypage";
	}

}
