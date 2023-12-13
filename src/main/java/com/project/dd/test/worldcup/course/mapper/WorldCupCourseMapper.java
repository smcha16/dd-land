package com.project.dd.test.worldcup.course.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.test.worldcup.course.domain.CourseDTO;
import com.project.dd.test.worldcup.course.domain.WorldCupCourseDTO;

public interface WorldCupCourseMapper {

    // 페이징
	int getTotalCount();
	
	List<CourseDTO> getAllCourse(Map<String, String> map);
	
	void updateCourseStatus(Map<String, String> map);

	int addCourse(CourseDTO dto);

	String getCourseSeq();

	int checkNameDuplication(CourseDTO dto);

	int addCWC(CourseDTO dto);
	
}
