package com.project.dd.ticket.reservation.domain;

import lombok.Data;

/**
 * 티켓 예약 세부정보를 담는 DTO 클래스입니다.
 * @author pega0
 *
 */
@Data
public class TicketReservationDTO {
	// Group
	private String user_group_book_seq;
	private String user_seq;
	private String group_book_seq;
	private String book_date;
	private String group_division;
	private String region;
	private String group_name;
	private String address;
	private String visit_date;
	private String visit_time;
	
	// Single
	private String ticket_book_seq;
	private String ea;
	private String price;
	private String ticket_seq;
	private String benefit_seq;
}
