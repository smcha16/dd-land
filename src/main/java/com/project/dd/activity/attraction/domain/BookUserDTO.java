package com.project.dd.activity.attraction.domain;

import lombok.Data;

@Data
public class BookUserDTO {

	private String book_user_seq;
	private String regdate;
	private String capacity;
	private String attraction_book_seq;
	private String user_seq;
	private String attraction_seq;
	
	private String attraction_name;
	private String user_name;
	private String email;
}
