package com.project.dd.test.worldcup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminWorldCupController {

	@GetMapping(value = "/admin/test/worldcup/view.do")
	public String view(Model model) {

		return "admin/test/worldcup/view";
	}

}
