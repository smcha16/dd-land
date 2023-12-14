package com.project.dd.close.attraction.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.close.attraction.domain.CloseAttractionDTO;

public interface CloseAttractionMapper {

	List<CloseAttractionDTO> list(Map<String, String> map);

	int getTotalCount();

	void delCloseAttr(String seq);

	List<AttractionDTO> attlist();

	int addCloseAtt(CloseAttractionDTO dto);

	CloseAttractionDTO getOne(String seq);

	int editClose(CloseAttractionDTO dto);

}
