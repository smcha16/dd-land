package com.project.dd.test.worldcup.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dd.test.worldcup.course.domain.CourseDTO;
import com.project.dd.test.worldcup.course.service.WorldCupCourseService;

@RestController
public class RestCourseController {

	@Autowired
	private WorldCupCourseService courseService;

	@PostMapping(value = "/admin/test/worldcup/course/name", produces = "application/json")
	public int checkName(@RequestBody CourseDTO dto) {
		
		return courseService.checkNameDuplication(dto);
	}

}
