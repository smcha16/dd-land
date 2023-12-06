package com.project.dd.communication.faq.domain;

import lombok.Data;

@Data
public class FaqDTO {
	
	private String faq_seq;
	private String type;
	private String question;
	private String answer;

}
