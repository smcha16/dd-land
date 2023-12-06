package com.project.dd.ticket.reservation.mapper;

import java.util.Map;

import com.project.dd.ticket.reservation.domain.TicketGroupReservationDTO;

public interface TicketReservationMapper {

	int groupReservation(TicketGroupReservationDTO dto);

	String getGroup();

	int usergroup(Map<String, String> map);

}
