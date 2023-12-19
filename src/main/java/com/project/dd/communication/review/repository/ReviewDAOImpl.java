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
	
	/* 총 개수 */

	@Override
	public int getTotalCount(Map<String, String> map) {
		
		return mapper.getTotalCount(map);
		
	}
	
	/* 목록 */

	@Override
	public List<ReviewDTO> getReviewList(Map<String, String> map) {
		
		return mapper.getReviewList(map);
		
	}
	
	/* 상세 */

	@Override
	public ReviewDTO getReview(String seq) {
		
		return mapper.getReview(seq);
		
	}
	
	/* 조회수 */

	@Override
	public void updateReadCount(String seq) {

		mapper.updateReadCount(seq);
		
	}
	
	/* 파일 수정 */
	
	@Override
	public void editFile(String seq) {

		mapper.editFile(seq);
		
	}
	
	/* 수정 */

	@Override
	public int editReview(ReviewDTO dto) {
		
		return mapper.editReview(dto);
		
	}
	
	/* 파일 삭제 */
	
	@Override
	public void deleteFile(String seq) {

		mapper.deleteFile(seq);
		
	}
	
	/* 삭제 */

	@Override
	public void deleteReview(String seq) {
		
		mapper.deleteReview(seq);
		
	}

}
