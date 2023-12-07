package com.project.dd.test.worldcup.attraction.repository;

import java.util.List;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.test.worldcup.attraction.domain.WorldCupAttractionDTO;

public interface WorldCupAttractionDAO {

    // 모든 어트랙션 정보 가져오기
    List<AttractionDTO> getAllAttractions(String close);

    // 어트랙션 월드컵에 등록된 모든 어트랙션 가져오기
    List<WorldCupAttractionDTO> getAllAWC(String isTest);

    // 어트랙션 월드컵에서 승리한 어트랙션 가져오기
    List<WorldCupAttractionDTO> getAllAWCWin();

    // 어트랙션 월드컵에서 최종 승리한 어트랙션 가져오기
    List<WorldCupAttractionDTO> getAllAWCFinalWin();

    // 선택되지 않은 어트랙션 가져오기
    List<AttractionDTO> getRemainingAttractions(List<String> selectedAttractions);

    // 리스트에서 두 개의 랜덤 어트랙션 가져오기
    List<AttractionDTO> getRandomTwoAttractions(List<AttractionDTO> attractions);
    
}
