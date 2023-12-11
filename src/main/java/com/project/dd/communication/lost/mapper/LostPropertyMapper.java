package com.project.dd.communication.lost.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.communication.lost.domain.LostPropertyDTO;

public interface LostPropertyMapper {

	int getTotalCount();

	List<LostPropertyDTO> getLostPropertyList(Map<String, String> map);

	int addLostProperty(LostPropertyDTO dto);

}
