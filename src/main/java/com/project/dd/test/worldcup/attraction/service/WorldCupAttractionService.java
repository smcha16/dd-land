package com.project.dd.test.worldcup.attraction.service;

import java.util.List;
import java.util.Map;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.test.worldcup.attraction.domain.WorldCupAttractionDTO;

public interface WorldCupAttractionService {

	int getTotalCount();
	
	int getTestCount();
	
	Map<String, String> paging(String solting, String searchStatus, String word, int page);
	
    List<AttractionDTO> getAllAttraction(Map<String, String> map);

	List<AttractionDTO> getAttractionList();
	
	void updateAttractionStatus(Map<String, String> map);
	
	int getAWCFinalWinTotalCount();
	
	List<AttractionDTO> getRandomTwoAttractions(List<AttractionDTO> remainingAttractions);

	List<AttractionDTO> getRemainingAttractions(List<String> selectedAttractions);

	int addAWC(AttractionDTO dto, String seq);
	
	int addAWCWin(AttractionDTO dto, String seq);
	
	int addAWCFinalWin(AttractionDTO dto, String seq);
	
	void updateAWCMatchCount(String attractionSeq);

	void updateAWCWinCount(String attractionSeq);

	void updateAWCFinalWinCount(String attractionSeq);

	List<AttractionDTO> getAttractionNameList();

}
