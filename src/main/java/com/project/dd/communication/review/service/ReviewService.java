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

@Service
public class ReviewService {
	
	@Autowired
	private ReviewDAO dao;
	
	/* 페이징 */

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
	
	/* 목록 */

	public List<ReviewDTO> getReviewList(Map<String, String> map) {
		
		List<ReviewDTO> list = dao.getReviewList(map);

		return list;
		
	}
	
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
	
	/* 상세 */

	public ReviewDTO getReview(String seq) {
		
		ReviewDTO dto = dao.getReview(seq);
		
		// 이메일
		
		String email = dto.getEmail();
		
	    email = email.substring(0, 2) + email.substring(2, email.indexOf('@')).replaceAll(".", "*") + email.substring(email.indexOf('@'));
	    
	    dto.setEmail(email);

		return dto;
		
	}
	
	/* 조회수 */

	public void updateReadCount(HttpSession session, String seq) {
		
		if (session.getAttribute("read") != null && session.getAttribute("read").toString().equals("n")) {

			dao.updateReadCount(seq);
			
			session.setAttribute("read", "y");
			
		}
		
	}

	/* 파일 수정 */

	public void editFile(String[] seqList, HttpServletRequest req, MultipartFile[] docs) {
			
		if (seqList.length > 0) {
				
			for (String seq : seqList) {
					
				dao.editFile(seq);
					
			}
			
		}
		
	}
	
	/* 수정 */

	public int editReview(ReviewDTO dto) {

		return dao.editReview(dto);
		
	}
	
	/* 파일 삭제 */
	
	public void deleteFile(String[] seqList) {

		for (String seq : seqList) {
			
			dao.deleteFile(seq);
			
		}
		
	}
	
	/* 삭제 */

	public void deleteReview(String[] seqList) {

		for (String seq : seqList) {
			
			dao.deleteReview(seq);
			
		}
		
	}

}
