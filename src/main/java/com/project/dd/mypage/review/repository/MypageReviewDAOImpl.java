package com.project.dd.mypage.review.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.mypage.review.domain.ReviewDTO;
import com.project.dd.mypage.review.domain.ReviewImgDTO;
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
	public int delete(String seq) {
		
		return mapper.delete(seq);
	}
	
	@Override
	public int imgDelete(String seq) {
		
		return mapper.imgDelete(seq);
	}
	
	@Override
	public int add(ReviewDTO dto) {
		
		return mapper.add(dto);
	}
	
	@Override
	public ReviewDTO get(String seq) {
		
		return mapper.get(seq);
	}
	
	@Override
	public int edit(ReviewDTO dto) {
		
		return mapper.edit(dto);
	}
	
	@Override
	public int getReviewSeq() {
		
		return mapper.getReviewSeq();
	}
	
	@Override
	public int addReviewImg(ReviewImgDTO idto) {
		
		return mapper.addReviewImg(idto);
	}

}
