package com.project.dd.test.worldcup.course.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.test.worldcup.course.service.WorldCupCourseService;

@Controller
public class AdminWorldCupCourseController {

    @Autowired
    private WorldCupCourseService courseService;
	/*
	 * @GetMapping(value = "/admin/test/course/view.do") public String
	 * view(@RequestParam(defaultValue = "1") int page, Model model,
	 * 
	 * @RequestParam(defaultValue = "Y") String isTest) {
	 * 
	 * Map<String, String> map = courseService.paging(page); // 페이징
	 * 
	 * model.addAttribute("currentPage", page); // 페이징 model.addAttribute("map",
	 * map); // 페이징 model.addAttribute("listCourse",
	 * courseService.getAllCourse(map));
	 * 
	 * return "admin/test/course/view"; }
	 */
}
