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
 * 2. 코스 테스트 개수 조회
 * 3. 코스 리스트 조회 (페이징 포함)
 * 4. 코스의 테스트 상태 업데이트
 * 5. 월드컵 최종 우승 코스의 총 개수 조회
 * 6. 남은 코스 중에서 랜덤으로 두 개 선택
 * 7. 코스 추가
 * 8. 추가한 코스 일련번호 조회
 * 9. 코스 경기 횟수 업데이트
 * 10. 코스 승리 횟수 업데이트
 * 11. 코스 최종 우승 횟수 업데이트
 * 12. 코스명 중복 체크
 * 13. 코스 상세 정보 조회
 * 14. 코스 수정
 * 15. 코스 삭제
 * 16. 코스 목록 조회
 * 17. 코스명 목록 조회
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
	 * 코스 테스트 개수를 조회합니다.
	 *
	 * @return 코스 테스트 개수
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
	 * 새로운 코스를 추가합니다.
	 *
	 * @param dto   추가할 코스의 정보를 담은 DTO 객체
	 * @param image 업로드된 이미지 파일
	 * @param req   HTTPServletRequest 객체
	 * @return 데이터베이스에 등록된 결과를 나타내는 정수값
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
	
	/**
	 * 현재 등록한 코스의 일련번호를 조회합니다.
	 *
	 * @return 등록한 코스의 일련번호
	 */
	@Override
	public String getCourseSeq() {
		return dao.getCourseSeq();
	}
	
	/**
	 * 코스의 월드컵 경기 전적을 추가합니다.
	 *
	 * @param dto       경기 전적을 추가할 코스의 정보를 담은 DTO 객체
	 * @param courseSeq 코스 일련번호
	 * @return 데이터베이스에 등록된 결과를 나타내는 정수값
	 */
	@Override
	public int addCWC(CourseDTO dto, String courseSeq) {

        dto.setCourse_seq(courseSeq);
        
		return dao.addCWC(dto);
	}
	
	/**
	 * 코스의 월드컵 승리 전적을 추가합니다.
	 *
	 * @param dto       승리 전적을 추가할 코스의 정보를 담은 DTO 객체
	 * @param courseSeq 코스 일련번호
	 * @return 데이터베이스에 등록된 결과를 나타내는 정수값
	 */
	@Override
	public int addCWCWin(CourseDTO dto, String courseSeq) {

        dto.setCourse_seq(courseSeq);
        
		return dao.addCWCWin(dto);
	}
	
	/**
	 * 코스의 월드컵 최종 우승 전적을 추가합니다.
	 *
	 * @param dto       최종 우승 전적을 추가할 코스의 정보를 담은 DTO 객체
	 * @param courseSeq 코스 일련번호
	 * @return 데이터베이스에 등록된 결과를 나타내는 정수값
	 */
	@Override
	public int addCWCFinalWin(CourseDTO dto, String courseSeq) {

        dto.setCourse_seq(courseSeq);
        
		return dao.addCWCFinalWin(dto);
	}
	
	/**
	 * 코스명 중복을 체크합니다.
	 *
	 * @param dto 코스 정보를 담은 DTO 객체
	 * @return 중복 여부를 나타내는 정수값
	 */
	@Override
	public int checkCourseNameDuplication(CourseDTO dto) {
		
		return dao.checkCourseNameDuplication(dto);
	}
	
	/**
	 * 월드컵 최종 우승 코스의 총 개수를 조회합니다.
	 *
	 * @return 최종 우승 코스의 총 개수
	 */
	@Override
	public int getCWCFinalWinTotalCount() {
		
		return dao.getCWCFinalWinTotalCount();
	}

	/**
	 * 특정 코스의 상세 정보를 조회합니다.
	 *
	 * @param courseSeq 조회할 코스의 일련번호
	 * @return 코스의 상세 정보
	 */
	@Override
	public CourseDTO getCourse(String courseSeq) {
		
		return dao.getCourse(courseSeq);
	}

	/**
	 * 코스의 정보를 수정하고 해당 코스와 관련된 이미지 파일을 업데이트합니다.
	 *
	 * @param dto   수정할 코스의 정보를 담은 DTO 객체
	 * @param image 업로드된 이미지 파일
	 * @param req   HTTPServletRequest 객체
	 * @return 데이터베이스에 수정 결과를 나타내는 정수값
	 */
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
	
	/**
	 * 선택된 코스들을 삭제합니다.
	 *
	 * @param course_seq 삭제할 코스들의 일련번호 배열
	 * @return 데이터베이스에 삭제 결과를 나타내는 정수값
	 */
	public int delCourse(String[] course_seq) {
		
		int result = 0;
		
		for (String seq : course_seq) {
			
			result += dao.delCourse(seq);
		}
		
		return result;
	}
	
	/**
	 * 선택된 코스의 월드컵 경기 전적을 삭제합니다.
	 *
	 * @param course_seq 삭제할 코스들의 일련번호 배열
	 * @return 데이터베이스에 삭제 결과를 나타내는 정수값
	 */
	@Override
	public int delCWC(String[] course_seq) {
		
		int result = 0;
		
		for (String seq : course_seq) {
			
			result += dao.delCWC(seq);
		}
		
		return result;
	}

	/**
	 * 선택된 코스의 월드컵 승리 전적을 삭제합니다.
	 *
	 * @param course_seq 삭제할 코스들의 일련번호 배열
	 * @return 데이터베이스에 삭제 결과를 나타내는 정수값
	 */
	@Override
	public int delCWCWin(String[] course_seq) {
		
		int result = 0;
		
		for (String seq : course_seq) {
			
			result += dao.delCWCWin(seq);
		}
		
		return result;
	}
	
	/**
	 * 선택된 코스의 월드컵 최종 우승 전적을 삭제합니다.
	 *
	 * @param course_seq 삭제할 코스들의 일련번호 배열
	 * @return 데이터베이스에 삭제 결과를 나타내는 정수값
	 */
	@Override
	public int delCWCFinalWin(String[] course_seq) {
		int result = 0;
		
		for (String seq : course_seq) {
			
			result += dao.delCWCFinalWin(seq);
		}
		
		return result;
	}
	
	/**
	 * 모든 코스 목록을 조회합니다.
	 *
	 * @return 모든 코스 목록
	 */
	@Override
	public List<CourseDTO> getCourseList() {
		return dao.getCourseList();
	}
	
	/**
	 * 남은 코스 중에서 랜덤으로 두 개의 코스를 선택하여 반환합니다.
	 *
	 * @param remainingCourses 선택 대상이 되는 코스 리스트
	 * @return 랜덤으로 선택된 두 개의 코스 리스트
	 */
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
	
	/**
	 * 선택된 코스를 제외한 나머지 코스 목록을 조회합니다.
	 *
	 * @param selectedCourses 이미 선택된 코스 일련번호 목록
	 * @return 선택되지 않은 남은 코스 목록
	 */
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
	
	/**
	 * 특정 코스의 월드컵 경기 횟수를 업데이트합니다.
	 *
	 * @param courseSeq 업데이트할 코스의 일련번호
	 */
	@Override
	public void updateCWCMatchCount(String courseSeq) {
		dao.updateCWCMatchCount(courseSeq);
	}
	
	/**
	 * 특정 코스의 월드컵 승리 횟수를 업데이트합니다.
	 *
	 * @param courseSeq 업데이트할 코스의 일련번호
	 */
	@Override
	public void updateCWCWinCount(String courseSeq) {
		dao.updateCWCWinCount(courseSeq);
	}
	
	/**
	 * 특정 코스의 월드컵 최종 우승 횟수를 업데이트합니다.
	 *
	 * @param courseSeq 업데이트할 코스의 일련번호
	 */
	@Override
	public void updateCWCFinalWinCount(String courseSeq) {
		dao.updateCWCFinalWinCount(courseSeq);
	}
	
	/**
	 * 모든 코스명 목록을 조회합니다.
	 *
	 * @return 모든 코스명 정보를 담은 리스트
	 */
	@Override
	public List<CourseDTO> getCourseNameList() {
		return dao.getCourseNameList();
	}
	
}
