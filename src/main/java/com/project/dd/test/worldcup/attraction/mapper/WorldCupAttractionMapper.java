package com.project.dd.test.worldcup.attraction.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.test.worldcup.attraction.domain.WorldCupAttractionDTO;

/**
 * 월드컵 어트랙션 매퍼 인터페이스입니다.
 * 
 * @author 이승원
 */
public interface WorldCupAttractionMapper {

    /**
	 * 전체 어트랙션 리스트를 조회합니다.
	 * 
	 * @param map 페이징을 위한 파라미터들이 담긴 맵
	 * @return 어트랙션 DTO의 리스트
	 */
    List<AttractionDTO> getAllAttraction(Map<String, String> map);
    
    /**
     * 운영중인 어트랙션 리스트를 조회합니다.
     * @param close 폐쇄 여부
     * @return 어트랙션 DTO의 리스트
     */
    List<AttractionDTO> getRunAttraction(String close);

    /**
     * 모든 어트랙션 월드컵 정보를 조회합니다.
     * @param isTest 테스트 채택 여부
     * @return 월드컵 어트랙션 DTO의 리스트
     */
    List<WorldCupAttractionDTO> getAllAWC(String isTest);

    /**
     * 모든 어트랙션 월드컵 승리 정보를 조회합니다.
     * @return 월드컵 어트랙션 DTO의 리스트
     */
    List<WorldCupAttractionDTO> getAllAWCWin();

    /**
     * 모든 어트랙션 월드컵 최종 승리 정보를 조회합니다.
     * @return 월드컵 어트랙션 DTO의 리스트
     */
    List<WorldCupAttractionDTO> getAllAWCFinalWin();

    /**
     * 페이징을 위한 전체 어트랙션 개수를 조회합니다.
     * @return 전체 어트랙션 개수
     */
    int getTotalCount();

    /**
     * 어트랙션의 상태를 업데이트합니다.
     * @param map 어트랙션 일련번호와 상태를 담은 맵
     */
    void updateAttractionStatus(Map<String, String> map);
    
}
