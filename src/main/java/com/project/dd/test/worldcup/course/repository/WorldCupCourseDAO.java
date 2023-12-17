package com.project.dd.test.worldcup.course.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.test.worldcup.course.domain.CourseDTO;

public interface WorldCupCourseDAO {

    // 페이징
	int getTotalCount();

	int getTestCount();

    List<CourseDTO> getAllCourse(Map<String, String> map);

	void updateCourseStatus(Map<String, String> map);

	int addCourse(CourseDTO dto);

	int checkNameDuplication(CourseDTO dto);

    String getCourseSeq();
    
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
	
    // 선택되지 않은 코스 가져오기
    //List<CourseDTO> getRemainingCourses(List<String> selectedCourses);

    // 리스트에서 두 개의 랜덤 코스 가져오기
    //List<CourseDTO> getRandomTwoCourses(List<CourseDTO> courses);

	void updateCWCMatchCount(String courseSeq);

	void updateCWCWinCount(String courseSeq);

	void updateCWCFinalWinCount(String courseSeq);

}
