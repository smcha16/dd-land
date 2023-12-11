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
	
	@GetMapping(value = "/template1.do")
	public String listTemplate1(Model model) {
		
		return "template.list1";
	}
	
	@GetMapping(value = "/template2.do")
	public String listTemplate2(Model model) {
		
		return "template.list2";
	}

	@GetMapping(value = "/template3.do")
	public String listTemplate3(Model model) {
		
		return "template.list3_toggle";
	}

	//template4는 이미지가 움직이지 않습니다..해시태그도 삭제하지 않은 구버전이니 template5를 사용해주세요!
	@GetMapping(value = "/template4.do")
	public String viewTemplate1(Model model) {
		
		return "template.view1";
	}

	@GetMapping(value = "/template5.do")
	public String viewTemplate2(Model model) {
		
		return "template.view2";
	}
	
}
