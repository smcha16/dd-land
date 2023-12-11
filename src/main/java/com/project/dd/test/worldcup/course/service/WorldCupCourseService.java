package com.project.dd.test.worldcup.course.service;

import java.util.List;
import java.util.Map;

import com.project.dd.test.worldcup.course.domain.CourseDTO;

public interface WorldCupCourseService {

	Map<String, String> paging(int page);

	List <CourseDTO> getAllCourse(Map<String, String> map);

	void updateCourseStatus(Map<String, String> map);

}
