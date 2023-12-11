package com.project.dd.communication.voc.repository;

import java.util.List;

import com.project.dd.communication.voc.domain.VocDTO;

public interface VocDAO {

	List<String> getVisitDateList(String email);
	
	int addVoc(VocDTO dto);

}
