package com.project.dd.test.worldcup.course.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.test.worldcup.course.domain.CourseDTO;
import com.project.dd.test.worldcup.course.service.WorldCupCourseService;

/**
 * 관리자가 월드컵 코스를 관리하는 컨트롤러입니다.
 * 
 * 1. 월드컵 코스 목록 조회 화면
 * 2. 월드컵 코스 테스트 화면
 * 3. 코스 상태 업데이트
 * 4. 월드컵 코스 추가 화면
 * 5. 월드컵 코스 추가 처리
 * 6. 월드컵 코스 수정 화면
 * 7. 월드컵 코스 수정 처리
 * 8. 월드컵 코스 삭제 처리
 * 
 * @author 이승원
 */
@Controller
@RequestMapping("/admin/test/worldcup/course")
public class AdminWorldCupCourseController {

	@Autowired
	private WorldCupCourseService cwcService;

	/**
     * 코스 목록 조회 화면을 표시합니다.
     * 
     * @param word  검색어
     * @param page  현재 페이지 번호
     * @param model 화면에 전달할 데이터를 담는 모델 객체
     * @return      월드컵 코스 목록 조회 화면
     */
	@GetMapping(value = "/list.do")
	public String list(String word, @RequestParam(defaultValue = "1") int page, Model model) {

		String solting = "admin";
		String searchStatus = (word == null || word.equals("")) ? "n" : "y";

		Map<String, String> map = cwcService.paging(solting, searchStatus, word, page); // 페이징
		
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("listCourse", cwcService.getAllCourse(map));

		return "admin/test/worldcup/course/list";

	}
	
	/**
     * 월드컵 코스 목록 조회 화면을 표시합니다.
     * 
     * @param word  검색어
     * @param page  현재 페이지 번호
     * @param model 화면에 전달할 데이터를 담는 모델 객체
     * @return      월드컵 코스 목록 조회 화면
     */
	@GetMapping(value = "/view.do")
	public String view(String word, @RequestParam(defaultValue = "1") int page, Model model) {

		String solting = "admin";
		String searchStatus = (word == null || word.equals("")) ? "n" : "y";

		Map<String, String> map = cwcService.paging(solting, searchStatus, word, page); // 페이징
		
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("listCourse", cwcService.getAllCourse(map));
		model.addAttribute("cwcFinalWinTotalCount", cwcService.getCWCFinalWinTotalCount());

		return "admin/test/worldcup/course/view";

	}

	 /**
     * 월드컵 코스 테스트 화면을 표시하고 코스 상태를 업데이트합니다.
     * 
     * @param courseSeq 코스 일련번호
     * @param isTest    테스트 여부 ("y" 또는 "n")
     * @param model     화면에 전달할 데이터를 담는 모델 객체
     * @return          코스 목록 조회 화면으로 리다이렉트
     */
	@PostMapping(value = "/view.do")
	public String updateCourseStatus(@RequestParam String courseSeq, @RequestParam String isTest, Model model) {
		// System.out.println("seq:" + courseSeq + " check:" + isTest);

		Map<String, String> map = new HashMap<>();
		map.put("isTest", isTest);
		map.put("courseSeq", courseSeq);

		cwcService.updateCourseStatus(map);

		return "redirect:/admin/test/worldcup/course/view.do";
	}
	
	/**
     * 월드컵 코스 추가 화면을 표시합니다.
     * 
     * @param model 화면에 전달할 데이터를 담는 모델 객체
     * @return      월드컵 코스 추가 화면
     */
	@GetMapping(value = "/add.do")
	public String add(Model model) {

		return "admin/test/worldcup/course/add";
	}

	/**
     * 월드컵 코스를 추가하고 테스트 관련 정보를 초기화합니다.
     * 
     * @param model   화면에 전달할 데이터를 담는 모델 객체
     * @param dto     추가할 코스 정보를 담은 DTO 객체
     * @param image   코스 이미지 파일
     * @param req     현재 HTTP 요청 객체
     * @return        코스 목록 조회 화면으로 리다이렉트
     */
	@PostMapping(value = "/addok.do")
	public String addok(Model model, CourseDTO dto, MultipartFile image, HttpServletRequest req) {
		// System.out.println("DTO: " + dto.toString());
		// System.out.println("Image File Name: " + image.getOriginalFilename());
		
		int result = cwcService.addCourse(dto, image, req);
		
		if (result > 0) {
			String courseSeq = cwcService.getCourseSeq();
			//System.out.println(courseSeq);
			
			 // 월드컵 코스 테스트 초기화
			cwcService.addCWC(dto, courseSeq);
			cwcService.addCWCWin(dto, courseSeq);
			cwcService.addCWCFinalWin(dto, courseSeq);
			
			return "redirect:/admin/test/worldcup/course/list.do";
		} else {
			model.addAttribute("alertMessage", "코스 추가에 실패했습니다.");
			return "redirect:/admin/test/worldcup/course/add.do";
		}
		
	}
	
	/**
     * 월드컵 코스 수정 화면을 표시합니다.
     * 
     * @param model 화면에 전달할 데이터를 담는 모델 객체
     * @param seq   수정할 코스의 일련번호
     * @return      월드컵 코스 수정 화면
     */
	@GetMapping(value = "/edit.do")
	public String edit(Model model, String seq) {
		
		CourseDTO dto = cwcService.getCourse(seq);
		String img = dto.getImg();
		
		//UUID 제거
		if (img.length() > 37 && img.contains("-") && img.contains("_")) {
			String originalFileName = img.substring(img.indexOf("_") + 1);
			dto.setImg(originalFileName);
		}
		
		model.addAttribute("dto", dto);
		
		return "admin/test/worldcup/course/edit";
	}
	
	/**
     * 사용자가 수정한 정보를 기반으로 월드컵 코스를 수정하고 결과에 따라 적절한 화면으로 리다이렉트합니다.
     * 
     * @param model 화면에 전달할 데이터를 담는 모델 객체
     * @param dto   수정할 코스 정보를 담은 DTO 객체
     * @param image 코스 이미지 파일
     * @param req   현재 HTTP 요청 객체
     * @return      코스 목록 조회 화면으로 리다이렉트
     */
	@PostMapping(value = "/editok.do")
	public String editok(Model model, CourseDTO dto, MultipartFile image, HttpServletRequest req) {

		int result = cwcService.editCourse(dto, image, req);
		
		if (result > 0) {
			return "redirect:/admin/test/worldcup/course/list.do";
		} else {
			return "redirect:/admin/test/worldcup/course/edit.do";
		}
	}
	
	/**
     * 선택한 월드컵 코스를 삭제하고 결과에 따라 적절한 화면으로 리다이렉트합니다.
     * 
     * @param model      화면에 전달할 데이터를 담는 모델 객체
     * @param course_seq 삭제할 코스의 일련번호 배열
     * @return           코스 목록 조회 화면으로 리다이렉트
     */
	@PostMapping(value = "/del.do")
	public String del(Model model, String[] course_seq) {

		cwcService.delCWC(course_seq);
		cwcService.delCWCWin(course_seq);
		cwcService.delCWCFinalWin(course_seq);
		
		// 월드컵 코스 삭제
		int result = cwcService.delCourse(course_seq);
		
		if (result > 0) {
			return "redirect:/admin/test/worldcup/course/list.do";
		} else {
			model.addAttribute("alertMessage", "코스 삭제에 실패했습니다.");
			return "redirect:/admin/test/worldcup/course/list.do";
		}
	}
	
}
