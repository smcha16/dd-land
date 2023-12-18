package com.project.dd.test.worldcup.course.controller;

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
import com.project.dd.test.worldcup.course.domain.CourseDTO;
import com.project.dd.test.worldcup.course.service.WorldCupCourseService;

@Controller
public class UserWorldCupCourseController {

    @Autowired
    private WorldCupCourseService cwcService;
    
    @GetMapping(value = "/user/test/worldcup/course/view.do")
	public String view(Model model, HttpSession session) {

		// 코스 리스트 가져오기
		List<CourseDTO> courseList = cwcService.getCourseList();

		// 선택하지 않은 코스 리스트 생성
		List<CourseDTO> remainingCourses = new ArrayList<>(courseList);

		// 선택하지 않은 코스 중에서 랜덤으로 두 개 선택
		List<CourseDTO> selectedTwoCourses = cwcService.getRandomTwoCourses(remainingCourses);

		model.addAttribute("selectedTwoCourses", selectedTwoCourses);
		model.addAttribute("testCount", cwcService.getTestCount());
		
		return "user/test/worldcup/course/view";
	}

	@GetMapping(value = "/user/test/worldcup/course/initialization.do")
	public String initialization(Model model, HttpSession session) {
		// 세션 초기화
		List<String> selectedCourses = new ArrayList<>();
		session.setAttribute("selectedCourses", selectedCourses);

		return "redirect:/user/test/worldcup/course/view.do";
	}

	@PostMapping("/user/test/worldcup/course/view.do")
	public ResponseEntity<Map<String, Object>> courseSelection(@RequestParam String winCourseSeq,
			@RequestParam String lostCourseSeq, Model model, HttpSession session) {
		// System.out.println("winCourseSeq " + winCourseSeq);
		// System.out.println("lostCourseSeq " + lostCourseSeq);

		cwcService.updateCWCMatchCount(winCourseSeq);
		cwcService.updateCWCWinCount(winCourseSeq);
		cwcService.updateCWCMatchCount(lostCourseSeq);

		// 세션에서 선택한 코스 리스트 가져오기
		@SuppressWarnings("unchecked") // 제네릭 경고 무시
		List<String> selectedCourses = (ArrayList<String>) session.getAttribute("selectedCourses");

		// 선택한 코스가 중복되지 않았을 경우 추가
		if (!selectedCourses.contains(lostCourseSeq)) {
			selectedCourses.add(lostCourseSeq);
			session.setAttribute("selectedCourses", selectedCourses);
		}

		// 두 개의 코스를 다시 선택
		List<CourseDTO> remainingCourses = cwcService.getRemainingCourses(selectedCourses);
		List<CourseDTO> selectedTwoCourses = cwcService.getRandomTwoCourses(remainingCourses);
		// System.out.println("remainingCourses " + remainingCourses);
		// System.out.println("selectedTwoCourses " + selectedTwoCourses);

		Map<String, Object> responseData = new HashMap<>();
		responseData.put("selectedTwoCourses", selectedTwoCourses);
		responseData.put("remainingCourses", remainingCourses);

		// HTTP status OK와 함께 JSON 형식 응답
		return new ResponseEntity<>(responseData, HttpStatus.OK);

	}

	@PostMapping("/user/test/worldcup/course/final.do")
	public ResponseEntity<String> finalUpdate(@RequestParam String finalWinCourseSeq) {
		cwcService.updateCWCFinalWinCount(finalWinCourseSeq);

		return new ResponseEntity<>("Final Win update completed", HttpStatus.OK);
	}
	
}
