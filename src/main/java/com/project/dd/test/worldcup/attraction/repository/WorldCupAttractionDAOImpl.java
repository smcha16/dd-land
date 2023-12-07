package com.project.dd.test.worldcup.attraction.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

	// 어트랙션 리스트 가져오기
	@Override
	public List<AttractionDTO> getAllAttractions(String close) {
		return mapper.listAttraction(close);
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

	// 선택되지 않은 어트랙션 가져오기
	@Override
	public List<AttractionDTO> getRemainingAttractions(List<String> selectedAttractions) {
		List<AttractionDTO> allAttractions = getAllAttractions("Y");
		List<AttractionDTO> remainingAttractions = new ArrayList<>();

		// 선택되지 않은 어트랙션 찾기
		for (AttractionDTO attraction : allAttractions) {
			if (!selectedAttractions.contains(attraction.getAttraction_seq())) {
				remainingAttractions.add(attraction);
			}
		}

		return remainingAttractions;
	}

	// 두 개의 랜덤 어트랙션 가져오기
	@Override
	public List<AttractionDTO> getRandomTwoAttractions(List<AttractionDTO> attractions) {
		List<AttractionDTO> selectedTwoAttractions = new ArrayList<>();

		Random random = new Random();

		// 최소한 두 개의 어트랙션이 있는 경우, 두 개를 랜덤으로 선택
		if (attractions.size() >= 2) {
			int index1 = random.nextInt(attractions.size());
			int index2;

			// index2가 index1과 다른지 확인하여 중복 방지
			do {
				index2 = random.nextInt(attractions.size());
			} while (index1 == index2);

			selectedTwoAttractions.add(attractions.get(index1));
			selectedTwoAttractions.add(attractions.get(index2));
		} else if (!attractions.isEmpty()) {
			// 어트랙션이 하나만 있는 경우, 그것을 리스트에 추가
			selectedTwoAttractions.add(attractions.get(0));
		}

		return selectedTwoAttractions;
	}
}