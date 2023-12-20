package com.project.dd.test.worldcup.attraction.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.activity.attraction.domain.AttractionDTO;

public interface WorldCupAttractionMapper {

	int getTotalCount();

	int getTestCount();
	
	List<AttractionDTO> getAllAttraction(Map<String, String> map);

	List<AttractionDTO> getAttractionList();
	
	void updateAttractionStatus(Map<String, String> map);

	int getAWCFinalWinTotalCount();

	int addAWC(AttractionDTO dto);

	int addAWCWin(AttractionDTO dto);

	int addAWCFinalWin(AttractionDTO dto);
	
	void updateAWCMatchCount(String attractionSeq);

	void updateAWCWinCount(String attractionSeq);

	void updateAWCFinalWinCount(String attractionSeq);

	List<AttractionDTO> getAttractionNameList();

	List<AttractionDTO> getTopThreeAttraction();

}
