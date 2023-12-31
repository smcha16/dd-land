package com.project.dd.test.worldcup.course.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.project.dd.test.worldcup.course.domain.CourseDTO;

public interface WorldCupCourseService {

	int getTotalCount();
	
	int getTestCount();
	
	Map<String, String> paging(String solting, String searchStatus, String word, int page);
	
	List <CourseDTO> getAllCourse(Map<String, String> map);

	void updateCourseStatus(Map<String, String> map);

	int addCourse(CourseDTO dto, MultipartFile image, HttpServletRequest req);

	int checkCourseNameDuplication(CourseDTO dto);
	
	String getCourseSeq();

	int addCWC(CourseDTO dto, String seq);

	int addCWCWin(CourseDTO dto, String seq);
	
	int addCWCFinalWin(CourseDTO dto, String seq);

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

	void updateCWCMatchCount(String seq);

	void updateCWCWinCount(String seq);

	void updateCWCFinalWinCount(String seq);

	List<CourseDTO> getCourseNameList();

}
