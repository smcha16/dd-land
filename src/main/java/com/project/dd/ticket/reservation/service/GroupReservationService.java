package com.project.dd.ticket.reservation.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.project.dd.ticket.reservation.domain.TicketReservationDTO;
import com.project.dd.ticket.reservation.repository.ReservationDAO;

@Service
public class GroupReservationService {

	@Autowired
	@Qualifier("GroupReservationDAOImpl")
	private ReservationDAO dao;

	public String getAddress(String postCode, String addressBasis, String addressDetail) {

		postCode = postCode.trim();
        addressBasis = addressBasis.trim();
        addressDetail = addressDetail.trim();
		
		return postCode + " " + addressBasis + " " + addressDetail;
	}

	public int groupReservation(TicketReservationDTO dto) {
		return dao.reservation(dto);
	}

	public String getGroup() {
		return dao.getSeq();
	}

	public int addUserGroup(Map<String, String> map) {
		return dao.addUserBook(map);
	}
	
}
