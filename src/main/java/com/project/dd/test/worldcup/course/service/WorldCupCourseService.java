package com.project.dd.test.worldcup.course.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.test.worldcup.course.domain.CourseDTO;

public interface WorldCupCourseService {

	int getTotalCount();
	
	int getTestCount();
	
	Map<String, String> paging(int page);

	List <CourseDTO> getAllCourse(Map<String, String> map);

	void updateCourseStatus(Map<String, String> map);

	int addCourse(CourseDTO dto, MultipartFile image, HttpServletRequest req);

	int checkCourseNameDuplication(CourseDTO dto);
	
	String getCourseSeq();

	int addCWC(CourseDTO dto, String courseSeq);

	int addCWCWin(CourseDTO dto, String courseSeq);
	
	int addCWCFinalWin(CourseDTO dto, String courseSeq);

	int getCWCFinalWinTotalCount();

	CourseDTO getCourse(String courseSeq);

	int editCourse(CourseDTO dto, MultipartFile image, HttpServletRequest req);

	int delCourse(String[] course_seq);

	int delCWC(String[] course_seq);

	int delCWCWin(String[] course_seq);

	int delCWCFinalWin(String[] course_seq);

	List<CourseDTO> getCourseList();

	List<CourseDTO> getRandomTwoCourses(List<CourseDTO> remainingCourses);
	
	List<CourseDTO> getRemainingCourses(List<String> selectedCourses);

	void updateCWCMatchCount(String courseSeq);

	void updateCWCWinCount(String courseSeq);

	void updateCWCFinalWinCount(String courseSeq);

	List<CourseDTO> getCourseNameList();
	
}
