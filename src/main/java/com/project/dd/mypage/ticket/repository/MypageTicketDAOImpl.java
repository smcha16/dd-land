package com.project.dd.mypage.ticket.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.mypage.ticket.domain.TicketDTO;
import com.project.dd.mypage.ticket.mapper.MypageTicketMapper;

@Primary
@Repository
public class MypageTicketDAOImpl implements MypageTicketDAO{
	
	@Autowired
	private MypageTicketMapper mapper;
	
	@Override
	public List<TicketDTO> list(Map<String, String> map) {
		
		return mapper.list(map);
	}
	
	@Override
	public int delete(String selectedTickets) {
		
		return mapper.delete(selectedTickets);
	}
	
	@Override
	public int getTotalCount() {
		
		return mapper.getTotalCount();
	}
	
	@Override
	public List<TicketDTO> plist(Map<String, String> map) {
		
		return mapper.plist(map);
	}
	
}
