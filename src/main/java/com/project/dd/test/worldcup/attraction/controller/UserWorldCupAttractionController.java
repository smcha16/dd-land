package com.project.dd.test.worldcup.attraction.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.test.worldcup.attraction.service.WorldCupAttractionService;

/**
 * 사용자가 참여하는 월드컵 어트랙션 테스트를 관리하는 컨트롤러입니다.
 * 
 * 1. 월드컵 어트랙션 목록 조회 화면 및 테스트 진행
 * 2. 월드컵 어트랙션 초기화
 * 3. 테스트 결과 업데이트
 * 4. 최종 우승 어트랙션 업데이트
 * 
 * @author 이승원
 */
@Controller
public class UserWorldCupAttractionController {

	@Autowired
	private WorldCupAttractionService awcService;

	/**
     * 월드컵 어트랙션 목록 조회 화면을 표시하고 테스트를 진행합니다.
     * 
     * @param model   화면에 전달할 데이터를 담는 모델 객체
     * @param session 현재 세션 객체
     * @return        월드컵 어트랙션 목록 조회 화면
     */
	@GetMapping(value = "/user/test/worldcup/attraction/view.do")
	public String view(Model model, HttpSession session) {

		// 어트랙션 리스트 가져오기
		List<AttractionDTO> attractionList = awcService.getAttractionList();

		// 선택하지 않은 어트랙션 리스트 생성
		List<AttractionDTO> remainingAttractions = new ArrayList<>(attractionList);

		// 선택하지 않은 어트랙션 중에서 랜덤으로 두 개 선택
		List<AttractionDTO> selectedTwoAttractions = awcService.getRandomTwoAttractions(remainingAttractions);

		model.addAttribute("selectedTwoAttractions", selectedTwoAttractions);
		model.addAttribute("testCount", awcService.getTestCount());
		
		return "user/test/worldcup/attraction/view";
	}

	/**
     * 세션을 초기화하고 월드컵 어트랙션 목록 조회 화면으로 리다이렉트합니다.
     * 
     * @param model   화면에 전달할 데이터를 담는 모델 객체
     * @param session 현재 세션 객체
     * @return        월드컵 어트랙션 목록 조회 화면으로 리다이렉트
     */
	@GetMapping(value = "/user/test/worldcup/attraction/initialization.do")
	public String initialization(Model model, HttpSession session) {
		// 세션 초기화
		List<String> selectedAttractions = new ArrayList<>();
		session.setAttribute("selectedAttractions", selectedAttractions);

		return "redirect:/user/test/worldcup/attraction/view.do";
	}

	/**
     * 사용자의 월드컵 어트랙션 테스트 결과를 업데이트하고 새로운 어트랙션을 선택합니다.
     * 
     * @param winAttractionSeq  이긴 어트랙션의 일련번호
     * @param lostAttractionSeq 진 어트랙션의 일련번호
     * @param model             화면에 전달할 데이터를 담는 모델 객체
     * @param session           현재 세션 객체
     * @return                  업데이트된 테스트 결과와 선택 가능한 어트랙션 목록을 응답
     */
	@PostMapping("/user/test/worldcup/attraction/view.do")
	public ResponseEntity<Map<String, Object>> attractionSelection(@RequestParam String winAttractionSeq,
			@RequestParam String lostAttractionSeq, Model model, HttpSession session) {

		// 테스트 결과 업데이트
		awcService.updateAWCMatchCount(winAttractionSeq);
		awcService.updateAWCWinCount(winAttractionSeq);
		awcService.updateAWCMatchCount(lostAttractionSeq);

		// 세션에서 선택한 어트랙션 리스트 가져오기
		@SuppressWarnings("unchecked") // 제네릭 경고 무시
		List<String> selectedAttractions = (ArrayList<String>) session.getAttribute("selectedAttractions");

		// 선택한 어트랙션이 중복되지 않았을 경우 추가
		if (!selectedAttractions.contains(lostAttractionSeq)) {
			selectedAttractions.add(lostAttractionSeq);
			session.setAttribute("selectedAttractions", selectedAttractions);
		}

		// 새로운 어트랙션 선택
		List<AttractionDTO> remainingAttractions = awcService.getRemainingAttractions(selectedAttractions);
		List<AttractionDTO> selectedTwoAttractions = awcService.getRandomTwoAttractions(remainingAttractions);

		// 응답 데이터 설정
		Map<String, Object> responseData = new HashMap<>();
		responseData.put("selectedTwoAttractions", selectedTwoAttractions);
		responseData.put("remainingAttractions", remainingAttractions);

		// HTTP status OK와 함께 JSON 형식 응답
		return new ResponseEntity<>(responseData, HttpStatus.OK);

	}

	/**
     * 최종 우승 어트랙션을 업데이트합니다.
     * 
     * @param finalWinAttractionSeq 최종 우승 어트랙션의 일련번호
     * @return                       업데이트 완료 메시지와 함께 HTTP status OK 응답
     */
	@PostMapping("/user/test/worldcup/attraction/final.do")
	public ResponseEntity<String> finalUpdate(@RequestParam String finalWinAttractionSeq) {
		// 최종 우승 어트랙션 업데이트
		awcService.updateAWCFinalWinCount(finalWinAttractionSeq);

		// HTTP status OK 응답
		return new ResponseEntity<>("Final Win update completed", HttpStatus.OK);
	}

}
