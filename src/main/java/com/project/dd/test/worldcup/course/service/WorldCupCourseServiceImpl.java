package com.project.dd.test.worldcup.course.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	
	@Override
    public int addCourse(CourseDTO dto, MultipartFile img) {
        // 이미지가 넘어왔을 경우에만 처리
        if (img != null && !img.isEmpty()) {
            try {
                UUID uuid = UUID.randomUUID();
                String filename = uuid + "_" + img.getOriginalFilename();

                //String filePath = req.getRealPath("/resources/files/test/worldcup/course") + File.separator + filename;
                //img.transferTo(new File(filePath));

                // 이미지 파일 경로를 DTO에 추가
                dto.setImg("/resources/files/test/worldcup/course/" + filename);

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("WorldCupCourseServiceImpl, addCourse: 이미지 처리 오류", e);
            }
        } else {
            // 이미지가 넘어오지 않을 경우 기본 이미지 경로 설정
            dto.setImg("/resources/files/test/worldcup/course/course.png");
        }

        // 테스트용 실제 경로 출력
        // System.out.println(req.getRealPath("/resources/files/test/worldcup/course"));

        return courseDAO.addCourse(dto);
    }

	@Override
	public int checkNameDuplication(CourseDTO dto) {
		
		return courseDAO.checkNameDuplication(dto);
	}

}
