package com.project.dd.test.worldcup.attraction.service;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.test.worldcup.attraction.domain.WorldCupAttractionDTO;

import java.util.List;

public interface WorldCupAttractionService {

    List<AttractionDTO> getAllAttraction();
    
    List<AttractionDTO> getRunAttraction(String close);

    List<WorldCupAttractionDTO> getAllAWC(String isTest);

    List<WorldCupAttractionDTO> getAllAWCWin();

    List<WorldCupAttractionDTO> getAllAWCFinalWin();

    List<AttractionDTO> getRemainingAttractions(List<String> selectedAttractions);

    List<AttractionDTO> getRandomTwoAttractions(List<AttractionDTO> attractions);

}
