package com.project.dd.communication.review.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.project.dd.communication.review.domain.ReviewDTO;
import com.project.dd.communication.review.repository.ReviewDAO;
import com.project.dd.login.domain.CustomUser;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewDAO dao;
	
	/* 페이징 */

	public Map<String, String> paging(String order, int page) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("order", order);

		int pageSize = 9;
		
		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));
		
		int totalPosts = dao.getTotalCount();
		int totalPages = (int)Math.ceil((double)totalPosts / pageSize);
		
		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));
		
		return map;
		
	}
	
	/* 목록 */

	public List<ReviewDTO> getReviewList(Authentication auth, Map<String, String> map) {
		
		List<ReviewDTO> list = dao.getReviewList(map);
		
		String level = ((CustomUser)auth.getPrincipal()).getDto().getAuth();

		for (ReviewDTO dto : list) {
			
			// 제목
			
			String subject = dto.getSubject();

            if (subject.length() > 13) {

                subject = subject.substring(0, 13) + " ...";
                
                dto.setSubject(subject);

            }
            
            // 내용

            if (level.equals("1")) {
            	
            	String content = dto.getContent();

                if (content.length() > 170) {

                	content = content.substring(0, 170) + " ...";
                	
                	dto.setContent(content);

                }
            	
            }

		}
		
		return list;
		
	}
	
	/* 상세 */

	public ReviewDTO getReview(String seq) {
		
		ReviewDTO dto = dao.getReview(seq);
		
		// 이메일
		
		String email = dto.getEmail();
		
	    email = email.substring(0, 2) + email.substring(2, email.indexOf('@')).replaceAll(".", "*") + email.substring(email.indexOf('@'));
	    
	    dto.setEmail(email);
	    
	    // 방문일
		
 		String visit_date = dto.getVisit_date().substring(0, 10);
 		
 		dto.setVisit_date(visit_date);
	    
	    // 등록일
	        
		String regdate = dto.getRegdate().substring(0, 10);
		
		dto.setRegdate(regdate);

		return dto;
		
	}
	
	/* 조회수 */

	public void updateReadCount(HttpSession session, String seq) {
		
		if (session.getAttribute("read") != null && session.getAttribute("read").toString().equals("n")) {

			dao.updateReadCount(seq);
			
			session.setAttribute("read", "y");
			
		}
		
	}

}
