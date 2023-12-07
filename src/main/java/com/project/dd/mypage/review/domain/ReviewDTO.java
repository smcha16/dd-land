package com.project.dd.mypage.review.domain;

import lombok.Data;

@Data
public class ReviewDTO {
	
	private String review_seq;
	private String subject;
	private String content;
	private String regdate;
	private String readcount;
	private String user_book_seq;

}
