package com.project.dd.communication.review.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.communication.review.domain.ReviewDTO;

public interface ReviewDAO {

	int getTotalCount();

	List<ReviewDTO> getReviewList(Map<String, String> map);

	ReviewDTO getReview(String seq);

	void updateReadCount(String seq);

}
