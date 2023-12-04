package com.project.dd.communication.notice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoticeUserController {
	
	@GetMapping(value = "/user/communication/notice/view.do")
	public String view(Model model) {

		return "user/communication/notice/view";

	}

}
