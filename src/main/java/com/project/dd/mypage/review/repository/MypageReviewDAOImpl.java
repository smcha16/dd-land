package com.project.dd.mypage.review.repository;

import java.util.List;

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
	public List<ReviewDTO> list() {
		
		return mapper.list();
	}

}
