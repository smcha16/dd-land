package com.project.dd.mypage.ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.mypage.ticket.domain.TicketDTO;
import com.project.dd.mypage.ticket.repository.MypageTicketDAO;

@Service
public class MypageTicketService {

	@Autowired
	private MypageTicketDAO dao;
	
	public List<TicketDTO> list() {
		
		return dao.list();
	}

	public String delete(String selectedTickets) {
		
		return dao.delete(selectedTickets);
	}

	


}
