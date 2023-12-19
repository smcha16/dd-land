package com.project.dd.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {
	
	@GetMapping(value = "/user/chat/view.do")
	public String view(Model model) {

		return "user/chat/view";
		
	}

	@GetMapping(value = "/user/chat/chat.do")
	public String chat(Model model) {

		return "user/chat/chat";
		
	}
	
}
