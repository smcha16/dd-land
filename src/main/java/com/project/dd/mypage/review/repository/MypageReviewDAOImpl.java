package com.project.dd.mypage.review.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.mypage.review.domain.ReviewDTO;
import com.project.dd.mypage.review.mapper.MypageReviewMapper;

@Primary
@Repository
public class MypageReviewDAOImpl implements MypageReviewDAO{
	
	@Autowired
	private MypageReviewMapper mapper;
	
	@Override
	public List<ReviewDTO> list(Map<String, String> map) {
		
		return mapper.list(map);
	}
	
	@Override
	public int getTotalCount() {
		
		return mapper.getTotalCount();
	}
	
	@Override
	public int delete(String selectedReview) {
		
		return mapper.delete(selectedReview);
	}
	
	@Override
	public int imgDelete(String selectedReview) {
		
		return mapper.imgDelete(selectedReview);
	}
	
	@Override
	public int add(ReviewDTO dto) {
		
		return mapper.add(dto);
	}

}
