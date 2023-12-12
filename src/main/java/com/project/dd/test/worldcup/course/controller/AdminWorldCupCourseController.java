package com.project.dd.test.worldcup.course.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.test.worldcup.course.service.WorldCupCourseService;

@Controller
public class AdminWorldCupCourseController {

	@Autowired
	private WorldCupCourseService courseService;
	
	@GetMapping(value = "/admin/test/worldcup/course/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, Model model,
			@RequestParam(defaultValue = "Y") String isTest) {
	
		Map<String, String> map = courseService.paging(page);

		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("listCourse", courseService.getAllCourse(map));

		return "admin/test/worldcup/course/view";
		
	}
	

	@PostMapping(value = "/admin/test/worldcup/course/view.do")
	public String updateCourseStatus(@RequestParam String courseSeq, @RequestParam String isTest, Model model) {
	    //System.out.println("seq:" + courseSeq + " check:" + isTest);
	    
	    Map<String, String> map = new HashMap<>();
	    map.put("isTest", isTest);
	    map.put("courseSeq", courseSeq);

	    courseService.updateCourseStatus(map);
	    
	    return "redirect:/admin/test/worldcup/course/view.do"; 
	}
	
}
