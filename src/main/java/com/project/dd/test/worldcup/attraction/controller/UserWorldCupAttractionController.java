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

import com.google.gson.JsonArray;
import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.test.worldcup.attraction.service.WorldCupAttractionService;

@Controller
public class UserWorldCupAttractionController {

	@Autowired
	private WorldCupAttractionService awcService;

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

	@GetMapping(value = "/user/test/worldcup/attraction/initialization.do")
	public String initialization(Model model, HttpSession session) {
		// 세션 초기화
		List<String> selectedAttractions = new ArrayList<>();
		session.setAttribute("selectedAttractions", selectedAttractions);

		return "redirect:/user/test/worldcup/attraction/view.do";
	}

	@PostMapping("/user/test/worldcup/attraction/view.do")
	public ResponseEntity<Map<String, Object>> attractionSelection(@RequestParam String winAttractionSeq,
			@RequestParam String lostAttractionSeq, Model model, HttpSession session) {

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

		// 두 개의 어트랙션을 다시 선택
		List<AttractionDTO> remainingAttractions = awcService.getRemainingAttractions(selectedAttractions);
		List<AttractionDTO> selectedTwoAttractions = awcService.getRandomTwoAttractions(remainingAttractions);

		Map<String, Object> responseData = new HashMap<>();
		responseData.put("selectedTwoAttractions", selectedTwoAttractions);
		responseData.put("remainingAttractions", remainingAttractions);

		// HTTP status OK와 함께 JSON 형식 응답
		return new ResponseEntity<>(responseData, HttpStatus.OK);

	}

	@PostMapping("/user/test/worldcup/attraction/final.do")
	public ResponseEntity<String> finalUpdate(@RequestParam String finalWinAttractionSeq) {
		awcService.updateAWCFinalWinCount(finalWinAttractionSeq);

		return new ResponseEntity<>("Final Win update completed", HttpStatus.OK);
	}

}
