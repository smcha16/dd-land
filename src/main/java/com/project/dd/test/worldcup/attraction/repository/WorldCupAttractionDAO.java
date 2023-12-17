package com.project.dd.test.worldcup.attraction.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.test.worldcup.attraction.domain.WorldCupAttractionDTO;

public interface WorldCupAttractionDAO {

    // 페이징
	int getTotalCount();

	int getTestCount();
	
    // 모든 어트랙션 가져오기
    List<AttractionDTO> getAllAttraction(Map<String, String> map);

	List<AttractionDTO> getAttractionList();
	
    // 운영중인 어트랙션 가져오기
    List<AttractionDTO> getRunAttraction(String close);

    // 어트랙션 월드컵에 등록된 모든 어트랙션 가져오기
    List<WorldCupAttractionDTO> getAllAWC(String isTest);

    // 어트랙션 월드컵에서 승리한 어트랙션 가져오기
    List<WorldCupAttractionDTO> getAllAWCWin();

    // 어트랙션 월드컵에서 최종 승리한 어트랙션 가져오기
    List<WorldCupAttractionDTO> getAllAWCFinalWin();

	void updateAttractionStatus(Map<String, String> map);

	int getAWCFinalWinTotalCount();

    // 선택되지 않은 어트랙션 가져오기
    //List<AttractionDTO> getRemainingAttractions(List<String> selectedAttractions);

    // 리스트에서 두 개의 랜덤 어트랙션 가져오기
    //List<AttractionDTO> getRandomTwoAttractions(List<AttractionDTO> attractions);

	int addAWC(AttractionDTO dto);

	int addAWCWin(AttractionDTO dto);
	
	int addAWCFinalWin(AttractionDTO dto);
	
	void updateAWCMatchCount(String attractionSeq);

	void updateAWCWinCount(String attractionSeq);

	void updateAWCFinalWinCount(String attractionSeq);

}
