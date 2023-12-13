package com.project.dd.test.worldcup.course.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.test.worldcup.course.domain.CourseDTO;

public interface WorldCupCourseDAO {

    // 페이징
	int getTotalCount();
	
    List<CourseDTO> getAllCourse(Map<String, String> map);

	void updateCourseStatus(Map<String, String> map);

	int addCourse(CourseDTO dto);

	int checkNameDuplication(CourseDTO dto);
	
}
