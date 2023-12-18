package com.project.dd.test.worldcup.attraction.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.activity.attraction.domain.AttractionDTO;

public interface WorldCupAttractionDAO {

    // 페이징
	int getTotalCount();

	int getTestCount();
	
    // 모든 어트랙션 가져오기
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

}
