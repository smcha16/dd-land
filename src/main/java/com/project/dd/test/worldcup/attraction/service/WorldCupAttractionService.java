package com.project.dd.test.worldcup.attraction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.test.worldcup.attraction.domain.WorldCupAttractionDTO;
import com.project.dd.test.worldcup.attraction.repository.WorldCupAttractionDAO;

@Service
public class WorldCupAttractionService {

    @Autowired
    @Qualifier("worldCupAttractionDAOImpl")
    private WorldCupAttractionDAO attractionDAO;

    // 어트랙션 목록을 가져오는 메서드
    public List<AttractionDTO> getAllAttractions(String close) {
        return attractionDAO.getAllAttractions(close);
    }

    // 모든 어트랙션 월드컵 정보를 가져오는 메서드
    public List<WorldCupAttractionDTO> getAllAWC(String isTest) {
        return attractionDAO.getAllAWC(isTest);
    }

    // 모든 어트랙션 월드컵 승리 정보를 가져오는 메서드
    public List<WorldCupAttractionDTO> getAllAWCWin() {
        return attractionDAO.getAllAWCWin();
    }

    // 모든 어트랙션 월드컵 최종 승리 정보를 가져오는 메서드
    public List<WorldCupAttractionDTO> getAllAWCFinalWin() {
        return attractionDAO.getAllAWCFinalWin();
    }

    // 선택된 어트랙션을 제외한 나머지 어트랙션을 가져오는 메서드
    public List<AttractionDTO> getRemainingAttractions(List<String> selectedAttractions) {
        return attractionDAO.getRemainingAttractions(selectedAttractions);
    }

    // 어트랙션 리스트에서 두 개의 랜덤 어트랙션을 가져오는 메서드
    public List<AttractionDTO> getRandomTwoAttractions(List<AttractionDTO> attractions) {
        return attractionDAO.getRandomTwoAttractions(attractions);
    }
    
}
