package com.project.dd.test.worldcup.attraction.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.test.worldcup.attraction.domain.WorldCupAttractionDTO;
import com.project.dd.test.worldcup.attraction.mapper.WorldCupAttractionMapper;

@Repository
@Primary
public class WorldCupAttractionDAOImpl implements WorldCupAttractionDAO {

	@Autowired
	private WorldCupAttractionMapper mapper;

	@Override
	public int getTotalCount() {
		return mapper.getTotalCount();
	}
	
	@Override
	public int getTestCount() {
		return mapper.getTestCount();
	}
	
	// 모든 어트랙션 리스트 가져오기
	@Override
	public List<AttractionDTO> getAllAttraction(Map<String, String> map) {
		return mapper.getAllAttraction(map);
	}
	
	@Override
	public List<AttractionDTO> getAttractionList() {
		return mapper.getAttractionList();
	}
	
	// 운영중인 어트랙션 리스트 가져오기
	@Override
	public List<AttractionDTO> getRunAttraction(String close) {
		return mapper.getRunAttraction(close);
	}

	// 어트랙션 월드컵 리스트 가져오기
	@Override
	public List<WorldCupAttractionDTO> getAllAWC(String isTest) {
		return mapper.getAllAWC(isTest);
	}

	// 어트랙션 월드컵 승리 리스트 가져오기
	@Override
	public List<WorldCupAttractionDTO> getAllAWCWin() {
		return mapper.getAllAWCWin();
	}

	// 어트랙션 월드컵 최종 승리 리스트 가져오기
	@Override
	public List<WorldCupAttractionDTO> getAllAWCFinalWin() {
		return mapper.getAllAWCFinalWin();
	}

	@Override
	public void updateAttractionStatus(Map<String, String> map) {
		mapper.updateAttractionStatus(map);
	}
	
	@Override
	public int getAWCFinalWinTotalCount() {
		return mapper.getAWCFinalWinTotalCount();
	}

//	@Override
//	public List<AttractionDTO> getRandomTwoAttractions(List<AttractionDTO> attractions) {
//		List<AttractionDTO> selectedTwoAttractions = new ArrayList<>();
//
//		Random random = new Random();
//
//		// 최소한 두 개의 어트랙션이 있는 경우, 두 개를 랜덤으로 선택
//		if (attractions.size() >= 2) {
//			int index1 = random.nextInt(attractions.size());
//			int index2;
//
//			// index2가 index1과 다른지 확인하여 중복 방지
//			do {
//				index2 = random.nextInt(attractions.size());
//			} while (index1 == index2);
//
//			selectedTwoAttractions.add(attractions.get(index1));
//			selectedTwoAttractions.add(attractions.get(index2));
//		} else if (!attractions.isEmpty()) {
//			// 어트랙션이 하나만 있는 경우, 그것을 리스트에 추가
//			selectedTwoAttractions.add(attractions.get(0));
//		}
//
//		return selectedTwoAttractions;
//	}
//
//	@Override
//	public List<AttractionDTO> getRemainingAttractions(List<String> selectedAttractions) {
//		List<AttractionDTO> allAttractions = getRunAttraction("Y");
//		List<AttractionDTO> remainingAttractions = new ArrayList<>();
//
//		// 선택되지 않은 어트랙션 찾기
//		for (AttractionDTO attraction : allAttractions) {
//			if (!selectedAttractions.contains(attraction.getAttraction_seq())) {
//				remainingAttractions.add(attraction);
//			}
//		}
//
//		return remainingAttractions;
//	}
	
	@Override
	public int addAWC(AttractionDTO dto) {
		return mapper.addAWC(dto);
	}
	
	@Override
	public int addAWCWin(AttractionDTO dto) {
		return mapper.addAWCWin(dto);
	}
	
	@Override
	public int addAWCFinalWin(AttractionDTO dto) {
		return mapper.addAWCFinalWin(dto);
	}

	@Override
	public void updateAWCMatchCount(String attractionSeq) {
		mapper.updateAWCMatchCount(attractionSeq);
	}
	
	@Override
	public void updateAWCWinCount(String attractionSeq) {
		mapper.updateAWCWinCount(attractionSeq);
	}
	
	@Override
	public void updateAWCFinalWinCount(String attractionSeq) {
		mapper.updateAWCFinalWinCount(attractionSeq);
	}
	
	@Override
	public List<AttractionDTO> getAttractionNameList() {
		return mapper.getAttractionNameList();
	}
	
}