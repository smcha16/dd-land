package com.project.dd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.dd.test.worldcup.attraction.service.WorldCupAttractionService;

@Controller
public class MainController {

	@Autowired
	private WorldCupAttractionService awcService;
	
	@GetMapping(value = "/index.do")
	public String index(Model model) {

		model.addAttribute("TopThreeAttraction", awcService.getTopThreeAttraction());
		
		return "main";
		
	}
	
	@GetMapping(value = "/admin/index.do")
	public String admin(Model model) {

		return "admin";
		
	}
	
	@GetMapping(value = "/mypage.do")
	public String mypage(Model model) {

		return "mypage";
		
	}
	
	@GetMapping(value = "/accesserror.do")
	public String accesserror(Model model) {

		return "accesserror";
		
	}
	
	@GetMapping(value = "/logout.do")
	public String logout(Model model) {

		return "logout";
		
	}
	
}
