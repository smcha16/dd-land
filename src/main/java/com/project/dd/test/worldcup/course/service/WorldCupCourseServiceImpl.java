package com.project.dd.test.worldcup.course.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.project.dd.test.worldcup.course.domain.CourseDTO;
import com.project.dd.test.worldcup.course.repository.WorldCupCourseDAO;

@Service
@Primary
public class WorldCupCourseServiceImpl implements WorldCupCourseService {

	@Autowired
	private WorldCupCourseDAO courseDAO;

	public Map<String, String> paging(int page) { // 페이징 메서드
		int pageSize = 10; // 조회할 글 개수

		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;

		Map<String, String> map = new HashMap<String, String>();

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));

		int totalPosts = courseDAO.getTotalCount();
		int totalPages = (int) Math.ceil((double) totalPosts / pageSize);

		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));

		return map;
	}
	
	@Override
	public List<CourseDTO> getAllCourse(Map<String, String> map) {
		return courseDAO.getAllCourse(map);
	}
	
	@Override
	public void updateCourseStatus(Map<String, String> map) {
		courseDAO.updateCourseStatus(map);
	}
	
}
