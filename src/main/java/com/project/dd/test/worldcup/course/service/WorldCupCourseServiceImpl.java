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

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.movie.domain.MovieDTO;
import com.project.dd.test.worldcup.course.domain.CourseDTO;
import com.project.dd.test.worldcup.course.domain.WorldCupCourseDTO;
import com.project.dd.test.worldcup.course.repository.WorldCupCourseDAO;

@Service
@Primary
public class WorldCupCourseServiceImpl implements WorldCupCourseService {

	@Autowired
	private WorldCupCourseDAO dao;

	public Map<String, String> paging(int page) { // 페이징 메서드
		int pageSize = 10; // 조회할 글 개수

		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;

		Map<String, String> map = new HashMap<String, String>();

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));

		int totalPosts = dao.getTotalCount();
		int totalPages = (int) Math.ceil((double) totalPosts / pageSize);

		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));

		return map;
	}

	@Override
	public List<CourseDTO> getAllCourse(Map<String, String> map) {
		return dao.getAllCourse(map);
	}

	@Override
	public void updateCourseStatus(Map<String, String> map) {
		dao.updateCourseStatus(map);
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
		
		return dao.addCourse(dto);
	}
	
	@Override
	public String getCourseSeq() {
		return dao.getCourseSeq();
	}
	
	@Override
	public int addCWC(CourseDTO dto, String courseSeq) {

        dto.setCourse_seq(courseSeq);
        
		return dao.addCWC(dto);
	}
	
	@Override
	public int addCWCWin(CourseDTO dto, String courseSeq) {

        dto.setCourse_seq(courseSeq);
        
		return dao.addCWCWin(dto);
	}
	
	@Override
	public int addCWCFinalWin(CourseDTO dto, String courseSeq) {

        dto.setCourse_seq(courseSeq);
        
		return dao.addCWCFinalWin(dto);
	}
	
	@Override
	public int checkNameDuplication(CourseDTO dto) {
		
		return dao.checkNameDuplication(dto);
	}
	
	@Override
	public int getCWCFinalWinTotalCount() {
		
		return dao.getCWCFinalWinTotalCount();
	}

	@Override
	public CourseDTO getCourse(String courseSeq) {
		
		return dao.getCourse(courseSeq);
	}

	@Override
	public int editCourse(CourseDTO dto, MultipartFile image, HttpServletRequest req) {
		
		if (image == null) {
			
			String imgFileName = dao.getCourseImgFileName(dto.getCourse_seq());
			
			dto.setImg(imgFileName);
			
		} else if (image.isEmpty()) {
			
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
		
		return dao.editCourse(dto);
	}
	
	public int delCourse(String[] course_seq) {
		
		int result = 0;
		
		for (String seq : course_seq) {
			
			result += dao.delCourse(seq);
		}
		
		return result;
	}
	
	@Override
	public int delCWC(String[] course_seq) {
		
		int result = 0;
		
		for (String seq : course_seq) {
			
			result += dao.delCWC(seq);
		}
		
		return result;
	}

	@Override
	public int delCWCWin(String[] course_seq) {
		
		int result = 0;
		
		for (String seq : course_seq) {
			
			result += dao.delCWCWin(seq);
		}
		
		return result;
	}
	
	@Override
	public int delCWCFinalWin(String[] course_seq) {
		int result = 0;
		
		for (String seq : course_seq) {
			
			result += dao.delCWCFinalWin(seq);
		}
		
		return result;
	}
	
}
