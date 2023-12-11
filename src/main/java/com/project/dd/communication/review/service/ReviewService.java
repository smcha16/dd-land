package com.project.dd.communication.review.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.communication.review.domain.ReviewDTO;
import com.project.dd.communication.review.repository.ReviewDAO;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewDAO dao;

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

	public List<ReviewDTO> getReviewList(Map<String, String> map) {
		
		List<ReviewDTO> list = dao.getReviewList(map);

		for (ReviewDTO dto : list) {
			
			String subject = dto.getSubject();

            if (subject.length() > 13) {

                subject = subject.substring(0, 13) + " ...";
                
                dto.setSubject(subject);

            }
            
            String content = dto.getContent();

            if (content.length() > 170) {

            	content = content.substring(0, 170) + " ...";
            	
            	dto.setContent(content);

            }
			
			String regdate = dto.getRegdate();
			
			regdate = regdate.substring(0, 10);
			
			dto.setRegdate(regdate);
			
		}
		
		return list;
		
	}

	public ReviewDTO getReview(String seq) {
		
		ReviewDTO dto = dao.getReview(seq);

		String regdate = dto.getRegdate().substring(0, 10);
		
		dto.setRegdate(regdate);
		
		String visit_date = dto.getVisit_date().substring(0, 10);
		
		dto.setVisit_date(visit_date);
		
		return dto;
		
	}

	public void updateReadCount(HttpSession session, String seq) {
		
		if (session.getAttribute("read") != null && session.getAttribute("read").toString().equals("n")) {

			dao.updateReadCount(seq);
			
			session.setAttribute("read", "y");
			
		}
		
	}

}
