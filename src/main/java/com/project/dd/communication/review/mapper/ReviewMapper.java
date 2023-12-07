package com.project.dd.communication.review.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.communication.review.domain.ReviewDTO;

public interface ReviewMapper {

	int getTotalCount();

	List<ReviewDTO> getReviewList(Map<String, String> map);

	ReviewDTO getReview(String seq);

}
