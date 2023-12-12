package com.project.dd.pb.benefit.repository;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.pb.benefit.domain.BenefitDTO;
import com.project.dd.pb.benefit.mapper.BenefitMapper;

import lombok.RequiredArgsConstructor;
@Primary
@Repository
@RequiredArgsConstructor
public class BenefitDAOImpl implements BenefitDAO{

	private final BenefitMapper mapper;
	
	@Override
	public List<BenefitDTO> cardList() {
		return mapper.cardList();
	}

	@Override
	public List<BenefitDTO> normalList() {
		return mapper.normalList();
	}

	@Override
	public List<BenefitDTO> list() {
		return mapper.list();
	}



	@Override
	public List<BenefitDTO> benefitList() {
		return mapper.benefitList();
	}

	@Override
	public String getName(String seq) {
		return mapper.getName(seq);
	}

	@Override
	public List<BenefitDTO> benefitInfo(String seq) {
		return mapper.benefitInfo(seq);
	}



	
}
