package com.project.dd.pb.benefit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.dd.pb.benefit.domain.BenefitDTO;
import com.project.dd.pb.benefit.mapper.BenefitMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BenefitService {

	private final BenefitMapper mapper;

	public List<BenefitDTO> cardList() {
		
		return mapper.cardList();
		
	}

	public List<BenefitDTO> normalList() {
		return mapper.normalList();
	}

	public List<BenefitDTO> List() {
		return mapper.list();
	}
	


	
}
