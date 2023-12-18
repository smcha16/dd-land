package com.project.dd.communication.review.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.communication.review.domain.ReviewDTO;

public interface ReviewMapper {

	int getTotalCount(Map<String, String> map);

	List<ReviewDTO> getReviewList(Map<String, String> map);

	ReviewDTO getReview(String seq);

	void updateReadCount(String seq);
	
	void editFile(String seq);

	int editReview(ReviewDTO dto);

	void deleteFile(String seq);
	
	void deleteReview(String seq);

}
