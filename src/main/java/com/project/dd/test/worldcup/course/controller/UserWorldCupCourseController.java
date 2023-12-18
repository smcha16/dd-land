package com.project.dd.test.worldcup.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.dd.test.worldcup.course.service.WorldCupCourseService;

@Controller
public class UserWorldCupCourseController {

    @Autowired
    private WorldCupCourseService cwcService;
    
	@GetMapping(value = "/user/test/worldcup/course/view.do")
	public String view(Model model) {

		return "user/test/worldcup/course/view";
	}
	
}
