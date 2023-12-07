package com.project.dd.ticket.reservation.mapper;

import java.util.Map;

import com.project.dd.ticket.reservation.domain.TicketReservationDTO;

public interface TicketReservationMapper {

	int groupReservation(TicketReservationDTO dto);

	String getGroupSeq();

	int addUserGroup(Map<String, String> map);

}
