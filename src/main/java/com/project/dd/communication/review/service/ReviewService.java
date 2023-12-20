package com.project.dd.communication.review.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.project.dd.communication.review.domain.ReviewDTO;
import com.project.dd.communication.review.domain.ReviewImgDTO;
import com.project.dd.communication.review.repository.ReviewDAO;

/**
 * 리뷰 서비스 클래스입니다.
 * 
 * @author sumin
 */
@Service
public class ReviewService {
	
	@Autowired
	private ReviewDAO dao;
	
	/**
	 * 페이징 처리를 위한 맵을 생성하는 메서드입니다.
	 *
	 * @param solting 정렬 기준
	 * @param searchStatus 검색 상태
	 * @param word 검색어
	 * @param page 페이지 번호
	 * @return 페이징 정보를 담은 Map 객체
	 */
	public Map<String, String> paging(String solting, String searchStatus, String word, int page) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("solting", solting);
		map.put("searchStatus", searchStatus);
		map.put("word", word);
		
		int pageSize = 0;
		
		if (solting.equals("user")) {
			
			pageSize = 9;
			
		} else if (solting.equals("admin")) {
			
			pageSize = 10;
			
		}
		
		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));
		
		int totalPosts = dao.getTotalCount(map);
		int totalPages = (int)Math.ceil((double)totalPosts / pageSize);
		
		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));
		
		return map;
		
	}
	
	/**
	 * 리뷰 목록을 가져오는 메서드입니다.
	 *
	 * @param map 페이징 정보가 담긴 Map 객체
	 * @return 리뷰 목록
	 */
	public List<ReviewDTO> getReviewList(Map<String, String> map) {
		
		List<ReviewDTO> list = dao.getReviewList(map);

		return list;
		
	}
	
	/**
	 * 리뷰 이미지 목록을 가져오는 메서드입니다.
	 *
	 * @param 리뷰 목록
	 * @return 리뷰 이미지 목록
	 */
	public String getReviewImgList(List<ReviewDTO> list) {
		
		List<ReviewImgDTO> iList = new ArrayList<ReviewImgDTO>();

		for (ReviewDTO dto : list) {
			
			for (ReviewImgDTO img_dto : dto.getImgList()) {
				
				if (img_dto.getImg() != null) {
					
					iList.add(img_dto);
					
				}
				
			}

		}
		
		Gson gson = new Gson();
		
		String imgList = gson.toJson(iList);
		
		return imgList;
		
	}
	
	/**
	 * 특정 리뷰의 상세 내용을 가져오는 메서드입니다.
	 *
	 * @param seq 조회할 리뷰의 일련번호
	 * @return 조회된 리뷰의 DTO 객체
	 */
	public ReviewDTO getReview(String seq) {
		
		ReviewDTO dto = dao.getReview(seq);
		
		// 이메일
		
		String email = dto.getEmail();
		
	    email = email.substring(0, 2) + email.substring(2, email.indexOf('@')).replaceAll(".", "*") + email.substring(email.indexOf('@'));
	    
	    dto.setEmail(email);

		return dto;
		
	}
	
	/**
	 * 조회수를 업데이트하는 메서드입니다.
	 *
	 * @param session 세션 객체
	 * @param seq 업데이트할 리뷰의 일련번호
	 */
	public void updateReadCount(HttpSession session, String seq) {
		
		if (session.getAttribute("read") != null && session.getAttribute("read").toString().equals("n")) {

			dao.updateReadCount(seq);
			
			session.setAttribute("read", "y");
			
		}
		
	}

	/**
	 * 파일을 수정하는 메서드입니다.
	 *
	 * @param seqList 수정할 리뷰 이미지의 일련번호 배열
	 * @param req HttpServletRequest 객체
	 * @param docs 첨부 파일 배열
	 */
	public void editFile(String[] seqList, HttpServletRequest req, MultipartFile[] docs) {
			
		if (seqList.length > 0) {
				
			for (String seq : seqList) {
					
				dao.editFile(seq);
					
			}
			
		}
		
	}
	
	/**
	 * 리뷰를 수정하는 메서드입니다.
	 *
	 * @param dto 수정할 리뷰의 DTO 객체
	 * @return 수정 결과 (1: 성공, 0: 실패)
	 */
	public int editReview(ReviewDTO dto) {

		return dao.editReview(dto);
		
	}
	
	/**
	 * 파일을 삭제하는 메서드입니다.
	 *
	 * @param seqList 삭제할 리뷰의 일련번호 배열
	 */
	public void deleteFile(String[] seqList) {

		for (String seq : seqList) {
			
			dao.deleteFile(seq);
			
		}
		
	}
	
	/**
	 * 여러 개의 리뷰를 삭제하는 메서드입니다.
	 *
	 * @param seqList 삭제할 리뷰의 일련번호 배열
	 */
	public void deleteReview(String[] seqList) {

		for (String seq : seqList) {
			
			dao.deleteReview(seq);
			
		}
		
	}

}
