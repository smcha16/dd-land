package com.project.dd.test.worldcup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserWorldCupController {

	@GetMapping(value = "/user/test/worldcup/view.do")
	public String view(Model model) {

		return "user/test/worldcup/view";
	}

}
