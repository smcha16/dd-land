package com.project.dd.ticket.reservation.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.pb.benefit.domain.BenefitDTO;
import com.project.dd.ticket.reservation.domain.TicketReservationDTO;
import com.project.dd.ticket.reservation.mapper.TicketReservationMapper;

@Repository("UserReservationDAOImpl")
@Primary
public class UserReservationDAOImpl implements ReservationDAO {

	@Autowired
	private TicketReservationMapper mapper;
	
	@Override
	public List<BenefitDTO> getBenefit(String type) {
		return mapper.getList(type);
	}
	
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
