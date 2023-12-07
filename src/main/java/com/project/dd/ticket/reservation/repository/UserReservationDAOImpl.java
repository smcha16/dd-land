package com.project.dd.ticket.reservation.repository;

import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.ticket.reservation.domain.TicketReservationDTO;

@Repository("UserReservationDAOImpl")
@Primary
public class UserReservationDAOImpl implements ReservationDAO {

	@Override
	public int reservation(TicketReservationDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getSeq() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addUserBook(Map<String, String> map) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
