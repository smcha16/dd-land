package com.project.dd.test.worldcup.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dd.test.worldcup.course.domain.CourseDTO;
import com.project.dd.test.worldcup.course.service.WorldCupCourseService;

/**
 * 월드컵 코스와 관련된 RESTful API를 처리하는 컨트롤러입니다.
 * 
 * 1. 코스 이름 중복 확인
 * 
 * @author 이승원
 */
@RestController
@RequestMapping("/admin/test/worldcup/course")
public class RestCourseController {

	@Autowired
	private WorldCupCourseService cwcService;

	/**
     * 코스 이름이 중복되는지 확인하고 결과를 반환합니다.
     * 
     * @param dto 코스 정보를 담은 DTO
     * @return    코스 이름 중복 여부를 나타내는 정수 값
     */
	@PostMapping(value = "/name", produces = "application/json")
	public int checkCourseName(@RequestBody CourseDTO dto) {
		
		return cwcService.checkCourseNameDuplication(dto);
	}
	
}
