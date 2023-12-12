package com.project.dd.communication.lost.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.communication.lost.domain.LostPropertyDTO;

public interface LostPropertyDAO {

	int getTotalCount();

	List<LostPropertyDTO> getLostPropertyList(Map<String, String> map);

	int addLostProperty(LostPropertyDTO dto);

	LostPropertyDTO getLostProperty(String seq);

	String getFileName(String seq);

	int editLostProperty(LostPropertyDTO dto);

	void deleteLostProperty(String seq);

}
