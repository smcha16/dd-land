package com.project.dd.communication.voc.mapper;

import java.util.List;

import com.project.dd.communication.voc.domain.VocDTO;

public interface VocMapper {
	
	List<String> getVisitDateList(String email);
	
	int addVoc(VocDTO dto);

}
