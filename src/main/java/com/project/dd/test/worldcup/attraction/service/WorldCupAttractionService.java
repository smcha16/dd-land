package com.project.dd.test.worldcup.attraction.service;

import java.util.List;
import java.util.Map;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.test.worldcup.attraction.domain.WorldCupAttractionDTO;

public interface WorldCupAttractionService {

    /**
     * 모든 어트랙션 리스트를 가져옵니다.
     */
    List<AttractionDTO> getAllAttraction(Map<String, String> map);

    /**
     * 운영 중인 어트랙션 리스트를 가져옵니다.
     */
    List<AttractionDTO> getRunAttraction(String close);

    /**
     * 어트랙션 월드컵에 등록된 모든 어트랙션 리스트를 가져옵니다.
     */
    List<WorldCupAttractionDTO> getAllAWC(String isTest);

    /**
     * 어트랙션 월드컵에서 승리한 어트랙션 리스트를 가져옵니다.
     */
    List<WorldCupAttractionDTO> getAllAWCWin();

    /**
     * 어트랙션 월드컵에서 최종 승리한 어트랙션 리스트를 가져옵니다.
     */
    List<WorldCupAttractionDTO> getAllAWCFinalWin();

    /**
     * 선택되지 않은 어트랙션 리스트를 가져옵니다.
     */
    List<AttractionDTO> getRemainingAttractions(List<String> selectedAttractions);

    /**
     * 리스트에서 두 개의 랜덤 어트랙션을 가져옵니다.
     */
    List<AttractionDTO> getRandomTwoAttractions(List<AttractionDTO> attractions);

    /**
     * 페이징을 위한 전체 어트랙션 개수를 조회합니다.
     */
	Map<String, String> paging(int page);

    /**
     * 어트랙션의 상태를 업데이트합니다.
     */
	void updateAttractionStatus(Map<String, String> map);

}
