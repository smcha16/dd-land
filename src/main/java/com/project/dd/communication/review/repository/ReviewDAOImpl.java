package com.project.dd.communication.review.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.communication.review.domain.ReviewDTO;
import com.project.dd.communication.review.mapper.ReviewMapper;

@Primary
@Repository
public class ReviewDAOImpl implements ReviewDAO {
	
	@Autowired
	private ReviewMapper mapper;

	@Override
	public int getTotalCount() {
		
		return mapper.getTotalCount();
		
	}

	@Override
	public List<ReviewDTO> getReviewList(Map<String, String> map) {
		
		return mapper.getReviewList(map);
		
	}

	@Override
	public ReviewDTO getReview(String seq) {
		
		return mapper.getReview(seq);
		
	}

	@Override
	public void updateReadCount(String seq) {

		mapper.updateReadCount(seq);
		
	}

}
