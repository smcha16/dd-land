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
	
	@GetMapping(value = "/template1.do")
	public String listTemplate1(Model model) {
		
		return "template.list1";
	}
	
	@GetMapping(value = "/template2.do")
	public String listTemplate2(Model model) {
		
		return "template.list2";
	}

	@GetMapping(value = "/template3.do")
	public String viewTemplate1(Model model) {
		
		return "template.view1";
	}

}
