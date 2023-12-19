package com.project.dd.communication.voc.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.communication.voc.domain.VocDTO;

public interface VocDAO {

	List<String> getVisitDateList(String email);
	
	int addVoc(VocDTO dto);

	int getTotalCount(Map<String, String> map);

	List<VocDTO> getVocList(Map<String, String> map);

	void editAnswer(VocDTO dto);

}
