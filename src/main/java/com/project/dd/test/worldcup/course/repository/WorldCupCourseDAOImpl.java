package com.project.dd.test.worldcup.course.repository;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
	    // tblCourse 추가
	    int result = mapper.addCourse(dto);

	    // 방금 등록한 course_seq 가져오기
	    int seq = mapper.getCourseSeq();

	    dto.setCourse_seq(seq + "");

	    // 이미지가 1장 이상이면 첫 번째 이미지만 사용
	    if (dto.getImg() != null && !dto.getImg().isEmpty()) {
	        // 이미지가 넘어왔을 경우에만 처리
	        try {
	            // 이미지 경로를 실제 파일로 복사
	            String sourcePath = dto.getImg();
	            String filename = UUID.randomUUID() + "_" + Paths.get(sourcePath).getFileName();
	            String filePath = "/resources/files/test/worldcup/course/" + filename;
	            
	            // 파일 복사
	            Files.copy(Paths.get(sourcePath), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

	            // 이미지 파일 경로를 DTO에 추가
	            dto.setImg(filePath);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException("WorldCupCourseDAOImpl, addCourse: 이미지 처리 오류", e);
	        }
	    } else {
	        // 이미지가 넘어오지 않을 경우 기본 이미지 경로 설정
	        dto.setImg("/resources/files/test/worldcup/course/course.png");
	    }

	    return result;
	}
	
	@Override
	public int checkNameDuplication(CourseDTO dto) {
		
	    return mapper.checkNameDuplication(dto);
	}
	
}
