package com.project.dd.mypage.ticket.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.mypage.ticket.domain.TicketDTO;

public interface MypageTicketDAO {

	List<TicketDTO> list(Map<String, String> map);

	int delete(String selectedTickets);

	int getTotalCount();

}
