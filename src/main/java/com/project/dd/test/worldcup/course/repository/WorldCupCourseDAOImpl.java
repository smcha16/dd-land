package com.project.dd.test.worldcup.course.repository;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.activity.movie.domain.MovieDTO;
import com.project.dd.test.worldcup.course.domain.CourseDTO;
import com.project.dd.test.worldcup.course.domain.WorldCupCourseDTO;
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
	public int checkNameDuplication(CourseDTO dto) {
		return mapper.checkNameDuplication(dto);
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

}
