package com.project.dd.guide.controller;

import java.util.List;
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

import com.project.dd.communication.notice.domain.NoticeDTO;
import com.project.dd.guide.domain.ConvenientDTO;
import com.project.dd.guide.service.ConvenientService;

/**
 * 관리자가 편의시설을 조회, 수정, 삭제, 추가하는 클래스
 * @author leeje
 *
 */

@Controller
@RequestMapping(value = "/admin/convenient")
public class AdminConvenientController {
	
	@Autowired
	private ConvenientService service;
	
	/**
	 * 편의시설 목록을 조회하여 보여주는 메서드
	 * @param category 카테고리
	 * @param word 검색어
	 * @param page 페이지 번호
	 * @param model Model 객체
	 * @return view 페이지
	 */
	//목록보기
	@GetMapping(value = "/view.do")
	public String view(String category, String word, @RequestParam(defaultValue = "1") int page, Model model) {
		
		String solting = "admin";
		
		String searchStatus = (word == null || word.equals("")) ? "n" : "y";
		
		Map<String, String> map = service.paging(solting, searchStatus, category, word, page);  //페이징
		
		List<ConvenientDTO> list = service.list(map);   
		
		model.addAttribute("currentPage", page);  //페이징
		model.addAttribute("map", map);  //페이징
		model.addAttribute("list", list);
		
		return "admin/convenient/view";
	}
	
	/**
	 * 편의시설을 추가하는 페이지로 이동하는 메서드
	 * @param model Model 객체
	 * @return 추가 폼 jsp
	 */
	//추가하기
	@GetMapping(value = "/add.do")
	public String add(Model model) {
		return "admin/convenient/add";
	}
	
	/**
	 * 편의시설을 추가하는 메서드
	 * @param model Model 객체
	 * @param dto 추가할 편의시설 정보를 담은 ConvenientDTO 객체
	 * @param req HttpServletRequest 객체
	 * @param image 이미지 파일
	 * @return 성공하면 목록 조회 페이지 / 실패하면 추가 페이지
	 */
	@PostMapping(value = "/addok.do")
	public String addok(Model model, ConvenientDTO dto, HttpServletRequest req, MultipartFile image) {
		
		ConvenientDTO conv = service.addFile(dto, req, image);
		
		int result = service.addConv(conv);
		
		if (result == 1) { 
	 		return "redirect:/admin/convenient/view.do";
	 	} else 
	 		return "redirect:/admin/convenient/add.do";
	}
	/**
	 * 편의시설을 삭제하는 메서드
	 * @param convenient_seq 삭제할 편의시설의 시퀀스 배열
	 * @return 목록 조회 페이지
	 */
	//삭제하기
	@PostMapping(value = "/del.do")
	public String del(String[] convenient_seq) {

		//편의시설 삭제 -> 편의시설 삭제, 위치 삭제
		
		int result = service.delConv(convenient_seq);
		
		if (result > 0) {
			return "redirect:/admin/convenient/view.do";
		} else {
			return "redirect:/admin/convenient/view.do";
		}
	}
	
	/**
	 * 편의시설을 수정하는 페이지로 이동하는 메서드
	 * @param model Model 객체
	 * @param seq 수정할 편의시설의 시퀀스
	 * @return 수정 폼 페이지
	 */
	//수정하기
	@GetMapping(value = "/edit.do")
	public String edit(Model model, String seq) {
		
		ConvenientDTO dto = service.one(seq);
		
		model.addAttribute("dto", dto);

		return "admin/convenient/edit";
	}
	
	/**
	 * 편의시설을 수정하는 메서드
	 * @param dto 수정할 편의시설 정보를 담은 ConvenientDTO 객체
	 * @param req HttpServletRequest 객체
	 * @param image 이미지 파일
	 * @return 성공하면 목록 조회 페이지 / 실패하면 수정 페이지
	 */
	@PostMapping(value = "/editok.do")
	public String editok(ConvenientDTO dto, HttpServletRequest req, MultipartFile image) {

		ConvenientDTO convenient = service.editFile(dto, req, image);

		int result = service.editConv(convenient);
		
		if (result == 1) {

	 		return "redirect:/admin/convenient/view.do";
	 
	 	} else {
	 
	 		return "redirect:/admin/convenient/edit.do";
	 
	 	}
	}

}
