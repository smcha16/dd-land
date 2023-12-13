package com.project.dd.close.attraction.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.close.attraction.domain.CloseAttractionDTO;

public interface CloseAttractionDAO {

	List<CloseAttractionDTO> list(Map<String, String> map);

	int getTotalCount();

	void del(String seq);

	List<AttractionDTO> attlist();

	int addCloseAtt(CloseAttractionDTO dto);

	CloseAttractionDTO getOne(String seq);

}
