package com.project.dd.mypage.review.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.mypage.review.domain.ReviewDTO;

public interface MypageReviewMapper {

	List<ReviewDTO> list(Map<String, String> map);

	int getTotalCount();

	int delete(String selectedReview);

	int imgDelete(String selectedReview);

}
