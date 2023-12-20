package com.project.dd.test.mbti.service;

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

import com.project.dd.test.mbti.domain.MBTIDTO;
import com.project.dd.test.mbti.repository.MBTIDAO;

/**
 * MBTI 추천 정보와 관련된 비즈니스 로직을 처리하는 서비스 클래스입니다.
 * 
 * 1. 총 MBTI 추천 정보 개수 조회
 * 2. MBTI 추천 정보 페이징 처리
 * 3. 모든 MBTI 추천 정보 조회
 * 4. 특정 MBTI 추천 정보 조회
 * 5. MBTI 추천 정보 추가
 * 6. MBTI 추천 정보 이름 중복 체크
 * 7. MBTI 추천 정보 수정
 * 8. MBTI 추천 정보 삭제
 * 
 * @author 이승원
 */
@Service
@Primary
public class MBTIServiceImpl implements MBTIService {

    @Autowired
    private MBTIDAO dao;

    /**
     * 모든 MBTI 추천 정보의 총 개수를 조회합니다.
     * 
     * @return MBTI 추천 정보의 총 개수
     */
    @Override
    public int getTotalCount() {
    	return dao.getTotalCount();
    }
    
    /**
     * MBTI 추천 정보 목록을 페이징 처리하여 반환합니다.
     * 
     * @param solting       정렬 기준
     * @param searchStatus  검색 상태
     * @param word          검색어
     * @param page          현재 페이지 번호
     * @param pageSize      조회할 글 개수
     * @return 페이징된 MBTI 추천 정보 목록 정보를 담은 Map 객체
     */
    public Map<String, String> paging(String solting, String searchStatus, String word, int page, int pageSize) {
    	
    	Map<String, String> map = new HashMap<String, String>();
		
    	map.put("solting", solting);
		map.put("searchStatus", searchStatus);
		map.put("word", word);
		
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
     * 모든 MBTI 추천 정보를 조회합니다.
     * 
     * @param map 페이징 정보를 담은 Map 객체
     * @return MBTI 추천 정보 목록
     */
    @Override
    public List<MBTIDTO> getAllMBTI(Map<String, String> map) {
        return dao.getAllMBTI(map);
    }

    /**
     * 특정 MBTI 추천 정보를 조회합니다.
     * 
     * @param seq 조회할 MBTI 추천 정보의 일련번호
     * @return 조회된 MBTI 추천 정보 정보
     */
    @Override
	public MBTIDTO getMBTI(String seq) {
		return dao.getMBTI(seq);
	}
    
    /**
     * MBTI 추천 정보를 추가합니다.
     * 
     * @param dto   추가할 MBTI 추천 정보 정보를 담은 DTO 객체
     * @param image 추가할 MBTI 추천 정보 이미지 파일
     * @param req   HTTP 서블릿 요청 객체
     * @return      추가 결과 (성공: 1, 실패: 0)
     */
    @Override
	public int addMBTI(MBTIDTO dto, MultipartFile image, HttpServletRequest req) {
		
		if (image.isEmpty()) {
			
			dto.setMbti_img("mbti.png");
			
		} else {
			try {
				
				UUID uuid = UUID.randomUUID();
				
				String filename = uuid + "_" + image.getOriginalFilename();
				
				image.transferTo(new File(req.getRealPath("/resources/files/test/mbti") + "\\" + filename));
				
				dto.setMbti_img(filename);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return dao.addMBTI(dto);
	}
    
    /**
     * MBTI 추천 정보의 이름이 중복되는지 체크합니다.
     * 
     * @param dto 체크할 MBTI 추천 정보 정보를 담은 DTO 객체
     * @return    중복 여부에 따른 결과 값 (0: 중복 없음, 1: 중복)
     */
    @Override
    public int checkMBTINameDuplication(MBTIDTO dto) {
    	return dao.checkMBTINameDuplication(dto);
    }

    /**
     * MBTI 추천 정보를 수정합니다.
     * 
     * @param dto   수정할 MBTI 추천 정보 정보를 담은 DTO 객체
     * @param image 수정할 MBTI 추천 정보 이미지 파일
     * @param req   HTTP 서블릿 요청 객체
     * @return      수정 결과 (성공: 1, 실패: 0)
     */
    @Override
	public int editMBTI(MBTIDTO dto, MultipartFile image, HttpServletRequest req) {
		
		if (image == null) {
			
			String imgFileName = dao.getMBTIImgFileName(dto.getMbti_seq());
			
			dto.setMbti_img(imgFileName);
			
		} else if (image.isEmpty()) {
			
			dto.setMbti_img("course.png");
		} else {
			try {
				
				UUID uuid = UUID.randomUUID();
				
				String filename = uuid + "_" + image.getOriginalFilename();
				
				image.transferTo(new File(req.getRealPath("/resources/files/test/mbti") + "\\" + filename));
				
				dto.setMbti_img(filename);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return dao.editMBTI(dto);
	}
    
    /**
     * 선택한 MBTI 추천 정보를 삭제합니다.
     * 
     * @param mbti_seq 삭제할 MBTI 추천 정보의 일련번호 배열
     * @return         삭제 결과 (성공: 1, 실패: 0)
     */
    @Override
    public int delMBTI(String[] mbti_seq) {
    	
		int result = 0;
		
		for (String seq : mbti_seq) {
			
			result += dao.delMBTI(seq);
		}
		
		return result;
    }
    
}
