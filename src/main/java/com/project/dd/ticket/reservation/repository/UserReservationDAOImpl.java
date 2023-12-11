package com.project.dd.ticket.reservation.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.pb.benefit.domain.BenefitDTO;
import com.project.dd.pb.price.domain.PriceDTO;
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
	public PriceDTO getPrice(String type, String age) {
		return mapper.getPrice(type, age);
	}
	
	@Override
	public int reservation(TicketReservationDTO dto) {
		return mapper.personalReservation(dto);
	}

	@Override
	public String getSeq() {
		return mapper.getPersonalSeq();
	}

	@Override
	public int addUserBook(Map<String, String> map) {
		return mapper.addUserBook(map);
	}
}
