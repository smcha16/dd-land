package com.project.dd.test.worldcup.attraction.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

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
	private WorldCupAttractionDAO dao;

	public Map<String, String> paging(int page) { // 페이징 메서드
		int pageSize = 10; // 조회할 글 개수

		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;

		Map<String, String> map = new HashMap<String, String>();

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));

		int totalPosts = dao.getTotalCount();
		int totalPages = (int) Math.ceil((double) totalPosts / pageSize);

		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));

		return map;
	}
	
	@Override
	public List<AttractionDTO> getAllAttraction(Map<String, String> map) {
		return dao.getAllAttraction(map);
	}

	@Override
	public List<AttractionDTO> getAttractionList() {
		return dao.getAttractionList();
	}
	
	@Override
	public List<AttractionDTO> getRunAttraction(String close) {
		return dao.getRunAttraction(close);
	}

	@Override
	public List<WorldCupAttractionDTO> getAllAWC(String isTest) {
		return dao.getAllAWC(isTest);
	}

	@Override
	public List<WorldCupAttractionDTO> getAllAWCWin() {
		return dao.getAllAWCWin();
	}

	@Override
	public List<WorldCupAttractionDTO> getAllAWCFinalWin() {
		return dao.getAllAWCFinalWin();
	}
	
	@Override
	public void updateAttractionStatus(Map<String, String> map) {
		dao.updateAttractionStatus(map);
	}

	@Override
	public int getAWCFinalWinTotalCount() {
		return dao.getAWCFinalWinTotalCount();
	}

	@Override
	public List<AttractionDTO> getRandomTwoAttractions(List<AttractionDTO> remainingAttractions) {
		ArrayList<AttractionDTO> selectedTwoAttractions = new ArrayList<>();

		Random random = new Random();

		// 어트랙션 리스트가 있고, 크기가 1보다 큰 경우
		if (remainingAttractions != null && remainingAttractions.size() > 1) {
			int index1 = random.nextInt(remainingAttractions.size());
			int index2;

			// index1과 다른 index2 선택 (중복 회피)
			do {
				index2 = random.nextInt(remainingAttractions.size());
			} while (index1 == index2);

			// 두 개의 어트랙션을 리스트에 추가
			selectedTwoAttractions.add(remainingAttractions.get(index1));
			selectedTwoAttractions.add(remainingAttractions.get(index2));
		} else if (remainingAttractions != null && remainingAttractions.size() == 1) {
			// 어트랙션이 하나만 남았을 경우
			selectedTwoAttractions.add(remainingAttractions.get(0));
		}

		return selectedTwoAttractions;
	}
	
	@Override
    public List<AttractionDTO> getRemainingAttractions(List<String> selectedAttractions) {
        List<AttractionDTO> allAttractions = getAttractionList();

        if (selectedAttractions == null) {
            return allAttractions;
        }

        return allAttractions.stream()
                .filter(attraction -> !selectedAttractions.contains(attraction.getAttraction_seq()))
                .collect(Collectors.toList());
    }

	@Override
	public int addAWC(AttractionDTO dto, String seq) {

		dto.setAttraction_seq(seq);
		
		return dao.addAWC(dto);
	}

	@Override
	public int addAWCWin(AttractionDTO dto, String seq) {

		dto.setAttraction_seq(seq);
		
		return dao.addAWCWin(dto);
	}

	@Override
	public int addAWCFinalWin(AttractionDTO dto, String seq) {

		dto.setAttraction_seq(seq);
		
		return dao.addAWCFinalWin(dto);
	}
	
	@Override
	public void updateAWCMatchCount(String attractionSeq) {
		dao.updateAWCMatchCount(attractionSeq);
	}
	
	@Override
	public void updateAWCWinCount(String attractionSeq) {
		dao.updateAWCWinCount(attractionSeq);
	}
	
	@Override
	public void updateAWCFinalWinCount(String attractionSeq) {
		dao.updateAWCFinalWinCount(attractionSeq);
	}
	
}
