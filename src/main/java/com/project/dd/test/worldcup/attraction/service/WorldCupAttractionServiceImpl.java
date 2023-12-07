package com.project.dd.test.worldcup.attraction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.test.worldcup.attraction.domain.WorldCupAttractionDTO;
import com.project.dd.test.worldcup.attraction.repository.WorldCupAttractionDAO;

@Service
@Primary
public class WorldCupAttractionServiceImpl implements WorldCupAttractionService {

    @Autowired
    private WorldCupAttractionDAO attractionDAO;

    @Override
    public List<AttractionDTO> getAllAttractions(String close) {
        return attractionDAO.getAllAttractions(close);
    }

    @Override
    public List<WorldCupAttractionDTO> getAllAWC(String isTest) {
        return attractionDAO.getAllAWC(isTest);
    }

    @Override
    public List<WorldCupAttractionDTO> getAllAWCWin() {
        return attractionDAO.getAllAWCWin();
    }

    @Override
    public List<WorldCupAttractionDTO> getAllAWCFinalWin() {
        return attractionDAO.getAllAWCFinalWin();
    }

    @Override
    public List<AttractionDTO> getRemainingAttractions(List<String> selectedAttractions) {
        return attractionDAO.getRemainingAttractions(selectedAttractions);
    }

    @Override
    public List<AttractionDTO> getRandomTwoAttractions(List<AttractionDTO> attractions) {
        return attractionDAO.getRandomTwoAttractions(attractions);
    }
}
