package com.project.dd.mypage.ticket.repository;

import java.util.List;

import com.project.dd.mypage.ticket.domain.TicketDTO;

public interface MypageTicketDAO {

	List<TicketDTO> list(String email);

	String delete(String selectedTickets);

}
