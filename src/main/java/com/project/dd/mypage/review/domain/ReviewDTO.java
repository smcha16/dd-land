package com.project.dd.mypage.review.domain;

import java.util.List;

import com.project.dd.activity.attraction.domain.AttractionImgDTO;

import lombok.Data;

@Data
public class ReviewDTO {
	
	private String review_seq;
	private String subject;
	private String content;
	private String regdate;
	private String readcount;
	private String user_book_seq;
	
	private List<ReviewImgDTO> imgList;

}
