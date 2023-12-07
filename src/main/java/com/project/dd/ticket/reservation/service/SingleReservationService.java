package com.project.dd.ticket.reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.project.dd.ticket.reservation.repository.ReservationDAO;

@Service
public class SingleReservationService {

	@Autowired
	@Qualifier("UserReservationDAOImpl")
	private ReservationDAO dao;
	
}
