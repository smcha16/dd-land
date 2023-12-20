package com.project.dd.test.mbti.controller;

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

import com.project.dd.test.mbti.domain.MBTIDTO;
import com.project.dd.test.mbti.service.MBTIService;
import com.project.dd.test.worldcup.attraction.service.WorldCupAttractionService;
import com.project.dd.test.worldcup.course.service.WorldCupCourseService;

/**
 * 관리자가 MBTI별 추천 정보를 관리하기 위한 컨트롤러입니다.
 * 
 * 1. MBTI 테스트 목록 조회 및 페이징 기능
 * 2. MBTI 테스트 추가, 수정, 삭제 기능
 * 3. 관련된 어트랙션 및 코스 정보를 제공 기능
 * 4. 이미지 업로드를 통한 MBTI 테스트 추가 및 수정 기능
 * 
 * @author 이승원
 */
@Controller
@RequestMapping("/admin/test/mbti")
public class AdminMBTIController {

    @Autowired
    private MBTIService mbtiService;

	@Autowired
	private WorldCupAttractionService awcService;

	@Autowired
	private WorldCupCourseService cwcService;
	
	/**
	 * MBTI 테스트 목록을 조회하여 페이징된 결과를 화면에 전달합니다.
	 * 
	 * @param word  검색어
	 * @param page  현재 페이지 번호
	 * @param model 화면에 전달할 데이터를 담는 모델 객체
	 * @return MBTI 테스트 목록 조회 화면
	 */
	@GetMapping(value = "/view.do")
	public String view(String word, @RequestParam(defaultValue = "1") int page, Model model) {

		String solting = "admin";
		String searchStatus = (word == null || word.equals("")) ? "n" : "y";

		Map<String, String> map = mbtiService.paging(solting, searchStatus, word, page, 10); // 페이징

		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("listMBTI", mbtiService.getAllMBTI(map));
		
		return "admin/test/mbti/view";
	}
	
	/**
	 * MBTI 테스트를 추가하기 위한 화면을 반환하며, 어트랙션과 코스 정보를 함께 제공합니다.
	 * 
	 * @param model 화면에 전달할 데이터를 담는 모델 객체
	 * @return MBTI 테스트 추가 화면
	 */
	@GetMapping(value = "/add.do")
	public String add(Model model) {

		model.addAttribute("attractionList", awcService.getAttractionNameList());
		model.addAttribute("courseList", cwcService.getCourseNameList());
		
		return "admin/test/mbti/add";
	}

	/**
	 * 사용자가 입력한 정보를 기반으로 MBTI 테스트를 추가하고 결과에 따라 적절한 화면으로 리다이렉트합니다.
	 * 
	 * @param model 화면에 전달할 데이터를 담는 모델 객체
	 * @param dto   MBTI 테스트 정보를 담은 DTO 객체
	 * @param image 업로드된 이미지 파일
	 * @param req   HTTP 요청 객체
	 * @return MBTI 테스트 목록 조회 화면 또는 추가 화면(실패 시)
	 */
	@PostMapping(value = "/addok.do")
	public String addok(Model model, MBTIDTO dto, MultipartFile image, HttpServletRequest req) {
		
		int result = mbtiService.addMBTI(dto, image, req);
		
		if (result > 0) {
			return "redirect:/admin/test/mbti/view.do";
		} else {
			model.addAttribute("alertMessage", "MBTI별 추천 추가에 실패했습니다.");
			return "redirect:/admin/test/mbti/add.do";
		}
	}
	
	/**
	 * 특정 MBTI 테스트를 수정하기 위한 화면을 반환하며, 기존 정보와 어트랙션, 코스 정보를 함께 제공합니다.
	 * 
	 * @param model 화면에 전달할 데이터를 담는 모델 객체
	 * @param seq   수정할 MBTI 테스트의 일련번호
	 * @return MBTI 테스트 수정 화면
	 */
	@GetMapping(value = "/edit.do")
	public String edit(Model model, String seq) {

		model.addAttribute("attractionList", awcService.getAttractionNameList());
		model.addAttribute("courseList", cwcService.getCourseNameList());
		
		MBTIDTO dto = mbtiService.getMBTI(seq);
		String img = dto.getMbti_img();
		
		//UUID 제거
		if (img.length() > 37 && img.contains("-") && img.contains("_")) {
			String originalFileName = img.substring(img.indexOf("_") + 1);
			dto.setMbti_img(originalFileName);
		}
		
		model.addAttribute("dto", dto);
		
		return "admin/test/mbti/edit";
	}
	
	/**
	 * 사용자가 수정한 정보를 기반으로 MBTI 테스트를 수정하고 결과에 따라 적절한 화면으로 리다이렉트합니다.
	 * 
	 * @param model
	 * @param dto
	 * @param image
	 * @param req
	 * @return
	 */
	@PostMapping(value = "/editok.do")
	public String editok(Model model, MBTIDTO dto, MultipartFile image, HttpServletRequest req) {

		int result = mbtiService.editMBTI(dto, image, req);
		
		if (result > 0) {
			return "redirect:/admin/test/mbti/view.do";
		} else {
			return "redirect:/admin/test/mbti/edit.do";
		}
	}
	
	/**
	 * 선택한 MBTI 테스트를 삭제하고 결과에 따라 적절한 화면으로 리다이렉트합니다.
	 * 
	 * @param model
	 * @param mbti_seq
	 * @return
	 */
	@PostMapping(value = "/del.do")
	public String del(Model model, String[] mbti_seq) {
		
		int result = mbtiService.delMBTI(mbti_seq);
		
		if (result > 0) {
			return "redirect:/admin/test/mbti/view.do";
		} else {
			model.addAttribute("alertMessage", "MBTI별 추천 삭제에 실패했습니다.");
			return "redirect:/admin/test/mbti/view.do";
		}
	}
	
}
