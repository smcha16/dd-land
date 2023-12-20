package com.project.dd.pb.benefit.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.pb.benefit.domain.BenefitDTO;

public interface BenefitDAO {

	List<BenefitDTO> cardList();

	List<BenefitDTO> normalList();

	List<BenefitDTO> list();


	List<BenefitDTO> benefitList();


	String getName(String seq);

	List<BenefitDTO> benefitInfo(String seq);

	int addBenefit(BenefitDTO benefitDTO);

	int del(String benefit_seq);

	int getTotalCount(String type);

	List<BenefitDTO> getBenefitList(Map<String, String> map);
	
	

}
