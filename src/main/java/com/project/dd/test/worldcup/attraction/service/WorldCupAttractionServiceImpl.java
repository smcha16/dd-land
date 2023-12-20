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
import com.project.dd.test.worldcup.attraction.repository.WorldCupAttractionDAO;

/**
 * 월드컵 어트랙션과 관련된 비즈니스 로직을 처리하는 서비스 클래스입니다.
 * 
 * 1. 어트랙션의 총 개수 조회
 * 2. 어트랙션 테스트 개수 조회
 * 3. 어트랙션 리스트 조회 (페이징 포함)
 * 4. 어트랙션의 테스트 상태 업데이트
 * 5. 월드컵 최종 우승 어트랙션의 총 개수 조회
 * 6. 남은 어트랙션 중에서 랜덤으로 두 개 선택
 * 7. 어트랙션 추가 및 관련 통계 업데이트
 * 8. 어트랙션 경기 횟수 업데이트
 * 9. 어트랙션 승리 횟수 업데이트
 * 10. 어트랙션 최종 우승 횟수 업데이트
 * 11. 어트랙션명 목록 조회
 * 12. 상위 3개 어트랙션 조회
 * 
 * @author 이승원
 */
@Service
@Primary
public class WorldCupAttractionServiceImpl implements WorldCupAttractionService {

	@Autowired
	private WorldCupAttractionDAO dao;

	/**
     * 어트랙션의 총 개수를 조회합니다.
     *
     * @return 어트랙션의 총 개수
     */
	@Override
	public int getTotalCount() {
		return dao.getTotalCount();
	}

	/**
     * 어트랙션 테스트 개수를 조회합니다.
     *
     * @return 어트랙션 테스트 개수
     */
	@Override
	public int getTestCount() {
		return dao.getTestCount();
	}

	/**
     * 어트랙션의 페이징 처리를 위한 맵을 생성합니다.
     *
     * @param solting       정렬 기준
     * @param searchStatus  검색 상태
     * @param word          검색어
     * @param page          현재 페이지 번호
     * @return 페이징 처리를 위한 맵
     */
	@Override
	public Map<String, String> paging(String solting, String searchStatus, String word, int page) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("solting", solting);
		map.put("searchStatus", searchStatus);
		map.put("word", word);
		
		int pageSize = 10; // 조회할 글 개수
		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));

		int totalPosts = getTotalCount();
		int totalPages = (int) Math.ceil((double) totalPosts / pageSize);

		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));

		return map;
	}
	
	/**
     * 전체 어트랙션 목록을 조회합니다.
     *
     * @param map 페이징 및 검색 정보를 담고 있는 맵
     * @return 전체 어트랙션 목록
     */
	@Override
	public List<AttractionDTO> getAllAttraction(Map<String, String> map) {
		return dao.getAllAttraction(map);
	}

	/**
     * 어트랙션 목록을 조회합니다.
     *
     * @return 어트랙션 목록
     */
	@Override
	public List<AttractionDTO> getAttractionList() {
		return dao.getAttractionList();
	}

	/**
     * 어트랙션의 테스트 상태를 업데이트합니다.
     *
     * @param map 테스트 상태를 업데이트하기 위한 맵
     */
	@Override
	public void updateAttractionStatus(Map<String, String> map) {
		dao.updateAttractionStatus(map);
	}

	/**
     * 월드컵 최종 우승 어트랙션의 총 개수를 조회합니다.
     *
     * @return 최종 우승 어트랙션의 총 개수
     */
	@Override
	public int getAWCFinalWinTotalCount() {
		return dao.getAWCFinalWinTotalCount();
	}

	/**
     * 남은 어트랙션 중에서 랜덤으로 두 개 선택하여 반환합니다.
     *
     * @param remainingAttractions 선택 대상이 되는 어트랙션 리스트
     * @return 랜덤으로 선택된 두 개의 어트랙션 리스트
     */
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
	
	/**
     * 선택되지 않은 어트랙션 목록을 가져옵니다.
     *
     * @param selectedAttractions 선택된 어트랙션 목록
     * @return 선택되지 않은 어트랙션 목록
     */
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

	 /**
     * 어트랙션 월드컵 정보를 추가합니다.
     *
     * @param dto 월드컵 정보를 담고 있는 어트랙션 DTO
     * @param seq 어트랙션 일련번호
     * @return 데이터베이스에 추가된 행 수
     */
	@Override
	public int addAWC(AttractionDTO dto, String seq) {

		dto.setAttraction_seq(seq);
		
		return dao.addAWC(dto);
	}

	/**
     * 어트랙션 월드컵 승리 결과를 추가합니다.
     *
     * @param dto 월드컵 승리 결과 정보를 담고 있는 어트랙션 DTO
     * @param seq 어트랙션 일련번호
     * @return 데이터베이스에 추가된 행 수
     */
	@Override
	public int addAWCWin(AttractionDTO dto, String seq) {

		dto.setAttraction_seq(seq);
		
		return dao.addAWCWin(dto);
	}

	/**
     * 어트랙션 월드컵 최종 우승 결과를 추가합니다.
     *
     * @param dto 월드컵 최종 우승 결과 정보를 담고 있는 어트랙션 DTO
     * @param seq 어트랙션 일련번호
     * @return 데이터베이스에 추가된 행 수
     */
	@Override
	public int addAWCFinalWin(AttractionDTO dto, String seq) {

		dto.setAttraction_seq(seq);
		
		return dao.addAWCFinalWin(dto);
	}
	
	/**
     * 어트랙션 월드컵 경기 횟수를 업데이트합니다.
     *
     * @param attractionSeq 어트랙션 일련번호
     */
	@Override
	public void updateAWCMatchCount(String attractionSeq) {
		dao.updateAWCMatchCount(attractionSeq);
	}
	
	/**
     * 어트랙션 월드컵 승리 횟수를 업데이트합니다.
     *
     * @param attractionSeq 어트랙션 일련번호
     */
	@Override
	public void updateAWCWinCount(String attractionSeq) {
		dao.updateAWCWinCount(attractionSeq);
	}
	
	/**
     * 어트랙션 월드컵 최종 우승 횟수를 업데이트합니다.
     *
     * @param attractionSeq 어트랙션 일련번호
     */
	@Override
	public void updateAWCFinalWinCount(String attractionSeq) {
		dao.updateAWCFinalWinCount(attractionSeq);
	}
	
	/**
     * 어트랙션명 목록을 조회합니다.
     *
     * @return 어트랙션명 목록
     */
	@Override
	public List<AttractionDTO> getAttractionNameList() {
		return dao.getAttractionNameList();
	}
	
	/**
     * 상위 3개 어트랙션을 조회합니다.
     *
     * @return 상위 3개 어트랙션 목록
     */
	@Override
	public List<AttractionDTO> getTopThreeAttraction() {
		return dao.getTopThreeAttraction();
	}
	
}
