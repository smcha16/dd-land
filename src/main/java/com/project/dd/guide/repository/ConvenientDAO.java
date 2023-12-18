package com.project.dd.guide.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.guide.domain.ConvenientDTO;

public interface ConvenientDAO {

	int getTotalCount();
	
	List<ConvenientDTO> list(Map<String, String> map);

	ConvenientDTO one(String seq);

	int checkLocationDuplication(ConvenientDTO dto);

	int checkNameDuplication(ConvenientDTO dto);
	
	int checkTelDuplication(ConvenientDTO dto);

	int addConv(ConvenientDTO dto);

	String getConvSeq();

	int addConvLocation(ConvenientDTO dto);

	int countConvenientLocation(String seq);

	int delConvenientLocation(String seq);

	int delConvenient(String seq);

	String getFileName(String convenient_seq);

	int editConv(ConvenientDTO convenient);

	int editConvLocation(ConvenientDTO convenient);

	

	
	

}
