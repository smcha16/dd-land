package com.project.dd.pb.price.repository;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.pb.price.domain.PriceDTO;
import com.project.dd.pb.price.mapper.PriceMapper;

import lombok.RequiredArgsConstructor;
@Primary
@Repository
@RequiredArgsConstructor
public class PriceDAOImpl implements PriceDAO{

	private final PriceMapper mapper;
	
	@Override
	public List<PriceDTO> personTypeList() {
		return mapper.personTypeList();
	}
	
	@Override
	public List<PriceDTO> groupTypeList() {
		return mapper.groupTypeList();
	}

	@Override
	public List<PriceDTO> list() {
		
		return mapper.list();
	}

	@Override
	public List<PriceDTO> tikcetTypeList() {
		return mapper.ticketTypeList();
	}

	@Override
	public List<PriceDTO> ageList() {
		return mapper.ageList();
	}

	@Override
	public PriceDTO getPriceInfo(String ticket_seq) {
		return mapper.getPriceInfo(ticket_seq);
	}

	@Override
	public int edit(PriceDTO priceDTO) {
		return mapper.edit(priceDTO);
	}

	


	
	
}
