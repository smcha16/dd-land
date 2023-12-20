package com.project.dd.ticket.reservation.domain;

import lombok.Data;

/**
 * 티켓 예약 정보를 담는 DTO 클래스입니다.
 * @author pega0
 *
 */
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
