package com.project.dd.test.worldcup.course.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.project.dd.test.worldcup.course.domain.CourseDTO;
import com.project.dd.test.worldcup.course.domain.WorldCupCourseDTO;

public interface WorldCupCourseService {

	Map<String, String> paging(int page);

	List <CourseDTO> getAllCourse(Map<String, String> map);

	void updateCourseStatus(Map<String, String> map);

	int addCourse(CourseDTO dto, MultipartFile image, HttpServletRequest req);

	int checkNameDuplication(CourseDTO dto);
	
	String getCourseSeq();

	int addCWC(CourseDTO dto, String courseSeq);

	int addCWCWin(CourseDTO dto, String courseSeq);
	
	int addCWCFinalWin(CourseDTO dto, String courseSeq);
	
}
