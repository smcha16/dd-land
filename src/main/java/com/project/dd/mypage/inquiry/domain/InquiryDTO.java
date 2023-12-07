package com.project.dd.mypage.inquiry.domain;

import lombok.Data;

@Data
public class InquiryDTO {
	
	private String inquiry_seq;
	private String type;
	private String subject;
	private String content;
	private String regdate;
	private String attach;
	private String answer;

}
