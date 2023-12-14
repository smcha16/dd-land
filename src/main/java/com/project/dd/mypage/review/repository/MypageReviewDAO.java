package com.project.dd.mypage.review.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.mypage.review.domain.ReviewDTO;
import com.project.dd.mypage.review.domain.ReviewImgDTO;

public interface MypageReviewDAO {

	List<ReviewDTO> list(Map<String, String> map);

	int getTotalCount();

	int add(ReviewDTO dto);

	ReviewDTO get(String seq);

	int edit(ReviewDTO dto);

	int delete(String seq);

	int imgDelete(String seq);

	int getReviewSeq();

	int addReviewImg(ReviewImgDTO idto);

}
