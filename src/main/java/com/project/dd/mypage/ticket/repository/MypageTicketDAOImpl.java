package com.project.dd.mypage.ticket.repository;

import java.util.List;

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
	public List<TicketDTO> list() {
		
		return mapper.list();
	}
	
	@Override
	public String delete(String selectedTickets) {
		
		return mapper.delete(selectedTickets);
	}
	

}
