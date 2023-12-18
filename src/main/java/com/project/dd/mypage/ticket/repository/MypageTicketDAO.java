package com.project.dd.mypage.ticket.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.mypage.ticket.domain.TicketDTO;

public interface MypageTicketDAO {

	List<TicketDTO> list(Map<String, String> map);

	int delete(String seq);

	int getTotalCount(String email);

	List<TicketDTO> plist(Map<String, String> map);

	int pGetTotalCount(String email);



}
