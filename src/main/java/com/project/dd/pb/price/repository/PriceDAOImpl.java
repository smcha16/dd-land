package com.project.dd.pb.price.repository;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
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

	
	
}
