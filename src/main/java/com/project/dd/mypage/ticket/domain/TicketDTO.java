package com.project.dd.mypage.ticket.domain;

import lombok.Data;

@Data
public class TicketDTO {
	
	private String email;
	private String ticket_type;
	private String person_type;
	private String age;
	private String book_date;
	private String visit_date;
	private String ea;
	private String total_price;
	private String user_book_seq;
	
}
