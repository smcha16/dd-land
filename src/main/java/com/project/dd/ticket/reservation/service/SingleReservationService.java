package com.project.dd.ticket.reservation.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.project.dd.pb.benefit.domain.BenefitDTO;
import com.project.dd.pb.price.domain.PriceDTO;
import com.project.dd.ticket.reservation.domain.TicketBookDTO;
import com.project.dd.ticket.reservation.domain.TicketReservationDTO;
import com.project.dd.ticket.reservation.repository.ReservationDAO;

@Service
public class SingleReservationService {

	@Autowired
	@Qualifier("UserReservationDAOImpl")
	private ReservationDAO dao;

	public List<BenefitDTO> getBenefit(String string) {
		
		List<BenefitDTO> list = dao.getBenefit(string);
		
		for (BenefitDTO dto : list) {
			dto.setStart_date(dto.getStart_date().substring(0, 10));
			dto.setEnd_date(dto.getEnd_date().substring(0, 10));
		}
		
		return list;
	}

	public PriceDTO getPrice(String type, String age) {
		return dao.getPrice(type, age);
	}

	public int personalReservation(TicketReservationDTO adto) {
		return dao.reservation(adto);
	}

	public String getPersonal() {
		return dao.getSeq();
	}

	public int addUserBook(Map<String, String> map) {
		return dao.addUserBook(map);
	}
	
}
