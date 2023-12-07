package com.project.dd.mypage.ticket.mapper;

import java.util.List;

import com.project.dd.mypage.ticket.domain.TicketDTO;

public interface MypageTicketMapper {

	List<TicketDTO> list();

	String delete(String selectedTickets);

}
