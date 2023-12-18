package com.project.dd.test.worldcup.course.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.test.worldcup.course.domain.CourseDTO;
import com.project.dd.test.worldcup.course.mapper.WorldCupCourseMapper;

@Repository
@Primary
public class WorldCupCourseDAOImpl implements WorldCupCourseDAO {

	@Autowired
	private WorldCupCourseMapper mapper;

	@Override
	public int getTotalCount() {
		return mapper.getTotalCount();
	}
	
	@Override
	public int getTestCount() {
		return mapper.getTestCount();
	}

	@Override
	public List<CourseDTO> getAllCourse(Map<String, String> map) {
		return mapper.getAllCourse(map);
	}

	@Override
	public void updateCourseStatus(Map<String, String> map) {
		mapper.updateCourseStatus(map);
	}

	@Override
	public String getCourseSeq() {
		return mapper.getCourseSeq();
	}
	
	@Override
	public int addCourse(CourseDTO dto) {
		return mapper.addCourse(dto);
	}
	
	@Override
	public int addCWC(CourseDTO dto) {
		return mapper.addCWC(dto);
	}

	@Override
	public int addCWCWin(CourseDTO dto) {
		return mapper.addCWCWin(dto);
	}
	
	@Override
	public int addCWCFinalWin(CourseDTO dto) {
		return mapper.addCWCFinalWin(dto);
	}
	
	@Override
	public int checkCourseNameDuplication(CourseDTO dto) {
		return mapper.checkCourseNameDuplication(dto);
	}
	
	@Override
	public int getCWCFinalWinTotalCount() {
		return mapper.getCWCFinalWinTotalCount();
	}

	@Override
	public CourseDTO getCourse(String courseSeq) {
		return mapper.getCourse(courseSeq);
	}
	
	@Override
	public int editCourse(CourseDTO dto) {
		return mapper.editCourse(dto);
	}

	@Override
	public String getCourseImgFileName(String courseSeq) {
		return mapper.getCourseImgFileName(courseSeq);
	}
	
	@Override
	public int delCourse(String seq) {
		return mapper.delCourse(seq);
	}
	
	@Override
	public int delCWC(String seq) {
		return mapper.delCWC(seq);
	}

	@Override
	public int delCWCWin(String seq) {
		return mapper.delCWCWin(seq);
	}
	
	@Override
	public int delCWCFinalWin(String seq) {
		return mapper.delCWCFinalWin(seq);
	}

	@Override
	public List<CourseDTO> getCourseList() {
		return mapper.getCourseList();
	}
	
//	@Override
//	public List<CourseDTO> getRandomTwoCourses(List<CourseDTO> courses) {
//		List<CourseDTO> selectedTwoCourses = new ArrayList<>();
//
//		Random random = new Random();
//
//		// 최소한 두 개의 어트랙션이 있는 경우, 두 개를 랜덤으로 선택
//		if (courses.size() >= 2) {
//			int index1 = random.nextInt(courses.size());
//			int index2;
//
//			// index2가 index1과 다른지 확인하여 중복 방지
//			do {
//				index2 = random.nextInt(courses.size());
//			} while (index1 == index2);
//
//			selectedTwoCourses.add(courses.get(index1));
//			selectedTwoCourses.add(courses.get(index2));
//		} else if (!courses.isEmpty()) {
//			// 어트랙션이 하나만 있는 경우, 그것을 리스트에 추가
//			selectedTwoCourses.add(courses.get(0));
//		}
//
//		return selectedTwoCourses;
//	}
//
//	@Override
//	public List<CourseDTO> getRemainingCourses(List<String> selectedCourses) {
//		List<CourseDTO> allCourses = getCourseList();
//		List<CourseDTO> remainingCourses = new ArrayList<>();
//
//		// 선택되지 않은 어트랙션 찾기
//		for (CourseDTO course : allCourses) {
//			if (!selectedCourses.contains(course.getCourse_seq())) {
//				remainingCourses.add(course);
//			}
//		}
//
//		return remainingCourses;
//	}
	
	@Override
	public void updateCWCMatchCount(String courseSeq) {
		mapper.updateCWCMatchCount(courseSeq);
	}
	
	@Override
	public void updateCWCWinCount(String courseSeq) {
		mapper.updateCWCWinCount(courseSeq);
	}
	
	@Override
	public void updateCWCFinalWinCount(String courseSeq) {
		mapper.updateCWCFinalWinCount(courseSeq);
	}
	
	@Override
	public List<CourseDTO> getCourseNameList() {
		return mapper.getCourseNameList();
	}
	
}
