package com.project.dd.ticket.reservation.domain;

import lombok.Data;

@Data
public class TicketBookDTO {
	private String ticket_book_seq;
	private String book_date;
	private String visit_date;
	private String ea;
	private String price;
	private String ticket_seq;
	private String benefit_seq;
}
