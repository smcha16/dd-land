package com.project.dd.communication.review.domain;

import java.util.List;

import lombok.Data;

@Data
public class ReviewDTO {
	
	private String review_seq;
	private String subject;
	private String content;
	private String regdate;
	private String readcount;
	private String user_book_seq;
	
	private String name;
	private String email;
	private String visit_date;

	private List<ReviewImgDTO> imgList;
	
}
