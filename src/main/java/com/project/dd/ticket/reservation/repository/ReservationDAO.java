package com.project.dd.ticket.reservation.repository;

import java.util.Map;

import com.project.dd.ticket.reservation.domain.TicketReservationDTO;

public interface ReservationDAO {

	int reservation(TicketReservationDTO dto);

	String getSeq();

	int addUserBook(Map<String, String> map);

}
