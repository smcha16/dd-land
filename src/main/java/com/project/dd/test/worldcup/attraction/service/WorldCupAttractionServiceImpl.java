package com.project.dd.test.worldcup.attraction.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public Map<String, String> paging(int page) { // 페이징 메서드
		int pageSize = 9; // 나타났으면 하는 개수

		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;

		Map<String, String> map = new HashMap<String, String>();

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));

		int totalPosts = attractionDAO.getTotalCount();
		int totalPages = (int) Math.ceil((double) totalPosts / pageSize);

		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));

		return map;
	}
	
	@Override
	public List<AttractionDTO> getAllAttraction(Map<String, String> map) {
		return attractionDAO.getAllAttraction(map);
	}

	@Override
	public List<AttractionDTO> getRunAttraction(String close) {
		return attractionDAO.getRunAttraction(close);
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
	public void updateAttractionStatus(Map<String, String> map) {
		attractionDAO.updateAttractionStatus(map);
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
