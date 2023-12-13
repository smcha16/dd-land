package com.project.dd.test.worldcup.course.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.test.worldcup.course.domain.CourseDTO;
import com.project.dd.test.worldcup.course.domain.WorldCupCourseDTO;
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

	@GetMapping(value = "/admin/test/worldcup/course/add.do")
	public String add(Model model) {

		return "admin/test/worldcup/course/add";
	}

	@PostMapping(value = "/admin/test/worldcup/course/addok.do")
	public String addok(Model model, CourseDTO dto, MultipartFile image, HttpServletRequest req) {
		System.out.println("DTO: " + dto.toString());
		System.out.println("Image File Name: " + image.getOriginalFilename());
		
		int result = courseService.addCourse(dto, image, req);
		
		CourseDTO cwcdto = new CourseDTO();
		
		if (result > 0) {
			String courseSeq = courseService.getCourseSeq();
			System.out.println(courseSeq);
			
			courseService.addCWC(cwcdto, courseSeq);
			
			return "redirect:/admin/test/worldcup/course/view.do";
		} else {
			model.addAttribute("alertMessage", "코스 추가에 실패했습니다.");
			return "redirect:/admin/test/worldcup/course/add.do";
		}
		
	}

	@PostMapping(value = "/admin/test/worldcup/course/view.do")
	public String updateCourseStatus(@RequestParam String courseSeq, @RequestParam String isTest, Model model) {
		// System.out.println("seq:" + courseSeq + " check:" + isTest);

		Map<String, String> map = new HashMap<>();
		map.put("isTest", isTest);
		map.put("courseSeq", courseSeq);

		courseService.updateCourseStatus(map);

		return "redirect:/admin/test/worldcup/course/view.do";
	}

}
