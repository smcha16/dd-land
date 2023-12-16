package com.project.dd.test.worldcup.course.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.test.worldcup.course.domain.CourseDTO;
import com.project.dd.test.worldcup.course.domain.WorldCupCourseDTO;
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
	
	@Override
	public int addCourse(CourseDTO dto, MultipartFile image, HttpServletRequest req) {
		
		if (image.isEmpty()) {
			
			dto.setImg("course.png");
			
		} else {
			
			try {
				
				UUID uuid = UUID.randomUUID();
				
				String filename = uuid + "_" + image.getOriginalFilename();
				
				image.transferTo(new File(req.getRealPath("/resources/files/test/worldcup/course") + "\\" + filename));
				
				dto.setImg(filename);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return courseDAO.addCourse(dto);
	}
	
	@Override
	public String getCourseSeq() {
		return courseDAO.getCourseSeq();
	}
	
	@Override
	public int addCWC(CourseDTO dto, String courseSeq) {

        dto.setCourse_seq(courseSeq);
        
		return courseDAO.addCWC(dto);
	}
	
	@Override
	public int addCWCWin(CourseDTO dto, String courseSeq) {

        dto.setCourse_seq(courseSeq);
        
		return courseDAO.addCWCWin(dto);
	}
	
	@Override
	public int addCWCFinalWin(CourseDTO dto, String courseSeq) {

        dto.setCourse_seq(courseSeq);
        
		return courseDAO.addCWCFinalWin(dto);
	}
	
	@Override
	public int checkNameDuplication(CourseDTO dto) {
		
		return courseDAO.checkNameDuplication(dto);
	}
	
	@Override
	public int getCWCFinalWinTotalCount() {
		
		return courseDAO.getCWCFinalWinTotalCount();
	}

}
