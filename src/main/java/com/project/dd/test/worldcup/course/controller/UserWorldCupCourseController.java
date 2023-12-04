package com.project.dd.test.worldcup.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserWorldCupCourseController {

	@GetMapping(value = "/user/test/worldcup/course/view.do")
	public String view(Model model) {

		return "user/test/worldcup/course/view";
	}
}
