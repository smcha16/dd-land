package com.project.dd.test.worldcup.course.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.test.worldcup.course.domain.CourseDTO;
import com.project.dd.test.worldcup.course.repository.WorldCupCourseDAO;

/**
 * 월드컵 코스와 관련된 비즈니스 로직을 처리하는 서비스 클래스입니다.
 *
 * 1. 코스의 총 개수 조회
 * 2. 코스 테스트 횟수 조회
 * 3. 코스 리스트 조회 (페이징 포함)
 * 4. 코스의 테스트 상태 업데이트
 * 5. 월드컵 최종 우승 코스의 총 개수 조회
 * 6. 남은 코스 중에서 랜덤으로 두 개 선택
 * 7. 코스 추가 및 관련 통계 업데이트
 * 8. 코스 경기 횟수 업데이트
 * 9. 코스 승리 횟수 업데이트
 * 10. 코스 최종 우승 횟수 업데이트
 * 11. 코스명 중복 체크
 * 12. 코스 상세 정보 조회
 * 13. 코스 수정
 * 14. 코스 삭제
 * 15. 코스 목록 조회
 * 16. 코스명 목록 조회
 * 
 * @author 이승원
 */
@Service
@Primary
public class WorldCupCourseServiceImpl implements WorldCupCourseService {

	@Autowired
	private WorldCupCourseDAO dao;

	/**
	 * 전체 코스의 개수를 조회합니다.
	 *
	 * @return 전체 코스 개수
	 */
	@Override
	public int getTotalCount() {
		return dao.getTotalCount();
	}
	
	/**
	 * 코스 테스트 횟수를 조회합니다.
	 *
	 * @return 코스 테스트 횟수
	 */
	@Override
	public int getTestCount() {
		return dao.getTestCount();
	}
	
	/**
	 * 코스의 페이징 처리를 위한 맵을 생성합니다.
	 *
	 * @param solting       정렬 기준
	 * @param searchStatus  검색 상태
	 * @param word          검색어
	 * @param page          현재 페이지 번호
	 * @return 페이징 처리를 위한 맵
	 */
	@Override
	public Map<String, String> paging(String solting, String searchStatus, String word, int page) {
		
		Map<String, String> map = new HashMap<String, String>();

		map.put("solting", solting);
		map.put("searchStatus", searchStatus);
		map.put("word", word);

		int pageSize = 10; // 조회할 글 개수
		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));

		int totalPosts = getTotalCount();
		int totalPages = (int) Math.ceil((double) totalPosts / pageSize);

		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));

		return map;
	}

	/**
	 * 전체 코스 목록을 조회합니다.
	 *
	 * @param map 페이징 및 검색 정보를 담고 있는 맵
	 * @return 전체 코스 목록
	 */
	@Override
	public List<CourseDTO> getAllCourse(Map<String, String> map) {
		return dao.getAllCourse(map);
	}

	/**
	 * 코스의 테스트 상태를 업데이트합니다.
	 *
	 * @param map 테스트 상태를 업데이트하기 위한 맵
	 */
	@Override
	public void updateCourseStatus(Map<String, String> map) {
		dao.updateCourseStatus(map);
	}
	
	/**
	 * 코스의 상세 정보를 조회합니다.
	 *
	 * @param courseSeq 조회할 코스의 일련번호
	 * @return          코스의 상세 정보
	 */
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
	public int checkCourseNameDuplication(CourseDTO dto) {
		
		return dao.checkCourseNameDuplication(dto);
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
	
	@Override
	public List<CourseDTO> getCourseList() {
		return dao.getCourseList();
	}
	
	@Override
	public List<CourseDTO> getRandomTwoCourses(List<CourseDTO> remainingCourses) {
		ArrayList<CourseDTO> selectedTwoCourses = new ArrayList<>();

		Random random = new Random();

		// 어트랙션 리스트가 있고, 크기가 1보다 큰 경우
		if (remainingCourses != null && remainingCourses.size() > 1) {
			int index1 = random.nextInt(remainingCourses.size());
			int index2;

			// index1과 다른 index2 선택 (중복 회피)
			do {
				index2 = random.nextInt(remainingCourses.size());
			} while (index1 == index2);

			// 두 개의 어트랙션을 리스트에 추가
			selectedTwoCourses.add(remainingCourses.get(index1));
			selectedTwoCourses.add(remainingCourses.get(index2));
		} else if (remainingCourses != null && remainingCourses.size() == 1) {
			// 어트랙션이 하나만 남았을 경우
			selectedTwoCourses.add(remainingCourses.get(0));
		}

		return selectedTwoCourses;
	}
	
	@Override
    public List<CourseDTO> getRemainingCourses(List<String> selectedCourses) {
        List<CourseDTO> allCourses = getCourseList();

        if (selectedCourses == null) {
            return allCourses;
        }

        return allCourses.stream()
                .filter(course -> !selectedCourses.contains(course.getCourse_seq()))
                .collect(Collectors.toList());
    }
	
	@Override
	public void updateCWCMatchCount(String courseSeq) {
		dao.updateCWCMatchCount(courseSeq);
	}
	
	@Override
	public void updateCWCWinCount(String courseSeq) {
		dao.updateCWCWinCount(courseSeq);
	}
	
	@Override
	public void updateCWCFinalWinCount(String courseSeq) {
		dao.updateCWCFinalWinCount(courseSeq);
	}
	
	@Override
	public List<CourseDTO> getCourseNameList() {
		return dao.getCourseNameList();
	}
	
}
