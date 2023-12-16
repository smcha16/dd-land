package com.project.dd.mypage.review.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.mypage.review.domain.ReviewDTO;

public interface MypageReviewDAO {

	List<ReviewDTO> list(Map<String, String> map);

	int getTotalCount();

	int delete(String selectedReview);

	int imgDelete(String selectedReview);

	int add(ReviewDTO dto);

	ReviewDTO get(String seq);

	int edit(ReviewDTO dto);



}
