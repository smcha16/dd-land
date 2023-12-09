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

/**
 * 월드컵 어트랙션과 관련된 비즈니스 로직을 처리하는 서비스 구현 클래스입니다.
 * 
 * @author 이승원
 */
@Service
@Primary
public class WorldCupAttractionServiceImpl implements WorldCupAttractionService {
	
	@Autowired
	private WorldCupAttractionDAO attractionDAO;

	/**
	 * 모든 어트랙션 리스트를 가져옵니다.
	 */
	@Override
	public List<AttractionDTO> getAllAttraction(Map<String, String> map) {
		return attractionDAO.getAllAttraction(map);
	}

	/**
	 * 운영 중인 어트랙션 리스트를 가져옵니다.
	 */
	@Override
	public List<AttractionDTO> getRunAttraction(String close) {
		return attractionDAO.getRunAttraction(close);
	}

	/**
	 * 어트랙션 월드컵 리스트를 가져옵니다.
	 */
	@Override
	public List<WorldCupAttractionDTO> getAllAWC(String isTest) {
		return attractionDAO.getAllAWC(isTest);
	}

	/**
	 * 어트랙션 월드컵 승리 리스트를 가져옵니다.
	 */
	@Override
	public List<WorldCupAttractionDTO> getAllAWCWin() {
		return attractionDAO.getAllAWCWin();
	}

	/**
	 * 어트랙션 월드컵 최종 승리 리스트를 가져옵니다.
	 */
	@Override
	public List<WorldCupAttractionDTO> getAllAWCFinalWin() {
		return attractionDAO.getAllAWCFinalWin();
	}

	/**
	 * 선택되지 않은 어트랙션 리스트를 가져옵니다.
	 */
	@Override
	public List<AttractionDTO> getRemainingAttractions(List<String> selectedAttractions) {
		return attractionDAO.getRemainingAttractions(selectedAttractions);
	}

	/**
	 * 두 개의 랜덤 어트랙션을 가져옵니다.
	 */
	@Override
	public List<AttractionDTO> getRandomTwoAttractions(List<AttractionDTO> attractions) {
		return attractionDAO.getRandomTwoAttractions(attractions);
	}

	/**
	 * 페이징을 위한 전체 어트랙션 개수를 조회합니다.
	 */
	@Override
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

	/**
	 * 어트랙션의 상태를 업데이트합니다.
	 */
	@Override
	public void updateAttractionStatus(Map<String, String> map) {
		attractionDAO.updateAttractionStatus(map);
	}

}
