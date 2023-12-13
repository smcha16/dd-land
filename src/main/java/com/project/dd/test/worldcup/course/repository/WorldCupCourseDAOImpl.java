package com.project.dd.test.worldcup.course.repository;

import java.util.List;
import java.util.Map;

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
	public List<CourseDTO> getAllCourse(Map<String, String> map) {
		return mapper.getAllCourse(map);
	}
	
	@Override
	public void updateCourseStatus(Map<String, String> map) {
		mapper.updateCourseStatus(map);
	}
	
	@Override
	public int addCourse(CourseDTO dto) {
		return mapper.addCourse(dto);
	}
	
	@Override
	public int checkNameDuplication(CourseDTO dto) {
	    return mapper.checkNameDuplication(dto);
	}
	
}
