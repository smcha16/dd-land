package com.project.dd.mypage.ticket.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.mypage.ticket.domain.TicketDTO;

public interface MypageTicketMapper {

	List<TicketDTO> list(Map<String, String> map);

	int delete(String selectedTickets);

	int getTotalCount(String email);

	List<TicketDTO> plist(Map<String, String> map);

	int count(String user_book_seq, String email);

	int pGetTotalCount(String email);


}
