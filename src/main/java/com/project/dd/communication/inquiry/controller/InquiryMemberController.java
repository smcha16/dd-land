package com.project.dd.communication.inquiry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InquiryMemberController {
	
	@GetMapping(value = "/user/communication/inquiry/view.do")
	public String view(Model model) {

		return "user/communication/inquiry/view";

	}

}
