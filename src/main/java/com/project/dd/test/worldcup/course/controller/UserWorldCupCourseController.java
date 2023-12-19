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

import com.project.dd.test.worldcup.course.domain.CourseDTO;
import com.project.dd.test.worldcup.course.service.WorldCupCourseService;

/**
 * 사용자가 월드컵 코스를 테스트하는 컨트롤러입니다.
 * 
 * 1. 코스 테스트 화면 표시
 * 2. 코스 초기화 처리
 * 3. 코스 선택 및 결과 업데이트 처리
 * 4. 최종 우승 코스 업데이트 처리
 * 
 * @author 이승원
 */
@Controller
public class UserWorldCupCourseController {

    @Autowired
    private WorldCupCourseService cwcService;
    
    /**
     * 코스 테스트 화면을 표시합니다.
     * 
     * @param model   화면에 전달할 데이터를 담는 모델 객체
     * @param session 현재 HTTP 세션 객체
     * @return        코스 테스트 화면
     */
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

    /**
     * 코스 테스트 관련 정보를 초기화하고 초기 상태로 화면을 갱신합니다.
     * 
     * @param model   화면에 전달할 데이터를 담는 모델 객체
     * @param session 현재 HTTP 세션 객체
     * @return        코스 테스트 화면으로 리다이렉트
     */
	@GetMapping(value = "/user/test/worldcup/course/initialization.do")
	public String initialization(Model model, HttpSession session) {
		// 세션 초기화
		List<String> selectedCourses = new ArrayList<>();
		session.setAttribute("selectedCourses", selectedCourses);

		return "redirect:/user/test/worldcup/course/view.do";
	}

	/**
     * 사용자가 선택한 코스와 결과를 업데이트하고 다음 라운드의 코스를 선택합니다.
     * 
     * @param winCourseSeq   이긴 코스의 일련번호
     * @param lostCourseSeq  진 코스의 일련번호
     * @param model          화면에 전달할 데이터를 담는 모델 객체
     * @param session        현재 HTTP 세션 객체
     * @return               다음 라운드의 코스 선택 화면으로 JSON 형식으로 응답
     */
	@PostMapping("/user/test/worldcup/course/view.do")
	public ResponseEntity<Map<String, Object>> courseSelection(@RequestParam String winCourseSeq,
			@RequestParam String lostCourseSeq, Model model, HttpSession session) {

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

		// 새로운 코스 선택
		List<CourseDTO> remainingCourses = cwcService.getRemainingCourses(selectedCourses);
		List<CourseDTO> selectedTwoCourses = cwcService.getRandomTwoCourses(remainingCourses);

		// 응답 데이터 설정
		Map<String, Object> responseData = new HashMap<>();
		responseData.put("selectedTwoCourses", selectedTwoCourses);
		responseData.put("remainingCourses", remainingCourses);

		// HTTP status OK와 함께 JSON 형식 응답
		return new ResponseEntity<>(responseData, HttpStatus.OK);

	}

	/**
     * 최종 우승 코스를 업데이트합니다.
     * 
     * @param finalWinCourseSeq 최종 우승 코스의 일련번호
     * @return                  업데이트 완료 메시지와 함께 HTTP status OK로 응답
     */
	@PostMapping("/user/test/worldcup/course/final.do")
	public ResponseEntity<String> finalUpdate(@RequestParam String finalWinCourseSeq) {
		// 최종 우승 코스 업데이트
		cwcService.updateCWCFinalWinCount(finalWinCourseSeq);

		// HTTP status OK 응답
		return new ResponseEntity<>("Final Win update completed", HttpStatus.OK);
	}
	
}
