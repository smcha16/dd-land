package com.project.dd.test.worldcup.course.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.test.worldcup.course.domain.CourseDTO;

public interface WorldCupCourseMapper {

	int getTotalCount();

	int getTestCount();
	
	List<CourseDTO> getAllCourse(Map<String, String> map);
	
	void updateCourseStatus(Map<String, String> map);

	int addCourse(CourseDTO dto);

	String getCourseSeq();

	int checkCourseNameDuplication(CourseDTO dto);

	int addCWC(CourseDTO dto);

	int addCWCWin(CourseDTO dto);
	
	int addCWCFinalWin(CourseDTO dto);

	int getCWCFinalWinTotalCount();

	CourseDTO getCourse(String courseSeq);

	int editCourse(CourseDTO dto);

	String getCourseImgFileName(String courseSeq);

	int delCourse(String seq);

	int delCWC(String seq);

	int delCWCWin(String seq);

	int delCWCFinalWin(String seq);

	List<CourseDTO> getCourseList();

	void updateCWCMatchCount(String courseSeq);

	void updateCWCWinCount(String courseSeq);

	void updateCWCFinalWinCount(String courseSeq);

	List<CourseDTO> getCourseNameList();
	
}
