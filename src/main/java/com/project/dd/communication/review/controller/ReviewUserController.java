package com.project.dd.communication.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReviewUserController {
	
	@GetMapping(value = "/user/communication/review/view.do")
	public String view(Model model) {

		return "user/communication/review/view";

	}

}
