package com.project.dd.mypage.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.mypage.review.domain.ReviewDTO;
import com.project.dd.mypage.review.repository.MypageReviewDAO;

@Service
public class MypageReviewService {
	
	@Autowired
	private MypageReviewDAO dao;

	public List<ReviewDTO> list() {
		
		return dao.list();
	}

}
