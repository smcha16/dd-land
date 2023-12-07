package com.project.dd.ticket.reservation.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.ticket.reservation.domain.TicketReservationDTO;
import com.project.dd.ticket.reservation.mapper.TicketReservationMapper;

@Repository("GroupReservationDAOImpl")
@Primary
public class GroupReservationDAOImpl implements ReservationDAO {
	
	@Autowired
	private TicketReservationMapper mapper;

	@Override
	public int reservation(TicketReservationDTO dto) {
		return mapper.groupReservation(dto);
	}
	
	@Override
	public String getSeq() {
		return mapper.getGroupSeq();
	}
	
	@Override
	public int addUserBook(Map<String, String> map) {
		return mapper.addUserGroup(map);
	}
	
}
