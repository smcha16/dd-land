package com.project.dd.communication.lost.controller;

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

import com.project.dd.communication.lost.domain.LostPropertyDTO;
import com.project.dd.communication.lost.service.LostPropertyService;

/**
 * 관리자용 분실물 컨트롤러 클래스입니다.
 * 
 * @author sumin
 */
@Controller
@RequestMapping("/admin/communication/lost-property")
public class AdminLostController {
	
	@Autowired
	private LostPropertyService service;
	
	/**
	 * 분실물 목록을 보여주는 메서드입니다.
	 *
	 * @param category 분실물 카테고리
	 * @param word 검색어
	 * @param start 검색 시작일
	 * @param end 검색 종료일
	 * @param page 페이지 번호
	 * @param model Spring의 Model 객체
	 * @return 분실물 목록을 보여주는 뷰의 경로
	 */
	@GetMapping(value = "/view.do")
	public String view(String category, String word, String start, String end, @RequestParam(defaultValue = "1") int page, Model model) {
		
		String searchStatus = (word == null || word.equals("")) ? "n" : "y";
		
		Map<String, String> map = service.paging(searchStatus, category, word, start, end, page);
		
		List<LostPropertyDTO> list = service.getLostPropertyList(map);
		
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("list", list);

		return "admin/communication/lost-property/view";

	}
	
	/**
	 * 분실물을 추가하는 폼을 보여주는 메서드입니다.
	 *
	 * @return 분실물 추가하는 폼을 보여주는 뷰의 경로
	 */
	@GetMapping(value = "/add.do")
	public String add() {

		return "admin/communication/lost-property/add";

	}
	
	/**
	 * 분실물을 추가하는 메서드입니다.
	 *
	 * @param dto 추가할 분실물의 DTO 객체
	 * @param req HttpServletRequest 객체
	 * @param doc 첨부 파일
	 * @return 분실물 목록을 보여주는 뷰로의 리다이렉트 경로 또는 분실물 추가 폼의 뷰로의 리다이렉트 경로
	 */
	@PostMapping(value = "/addok.do")
	public String addOk(LostPropertyDTO dto, HttpServletRequest req, MultipartFile doc) {
		
		LostPropertyDTO lost = service.addFile(dto, req, doc);

		int result = service.addLostProperty(lost);
		
		if (result == 1) {

	 		return "redirect:/admin/communication/lost-property/view.do";
	 
	 	} else {
	 
	 		return "redirect:/admin/communication/lost-property/add.do";
	 
	 	}

	}
	
	/**
	 * 분실물을 수정하는 폼을 보여주는 메서드입니다.
	 *
	 * @param seq 수정할 분실물의 일련번호
	 * @param model Spring의 Model 객체
	 * @return 분실물 수정 폼을 보여주는 뷰의 경로
	 */
	@GetMapping(value = "/edit.do")
	public String edit(String seq, Model model) {
		
		LostPropertyDTO dto = service.getLostProperty(seq);
		
		model.addAttribute("dto", dto);

		return "admin/communication/lost-property/edit";

	}
	
	/**
	 * 분실물을 수정하는 메서드입니다.
	 *
	 * @param dto 수정할 분실물의 DTO 객체
	 * @param req HttpServletRequest 객체
	 * @param doc 첨부 파일
	 * @return 분실물 목록을 보여주는 뷰로의 리다이렉트 경로 또는 분실물 수정 폼의 뷰로의 리다이렉트 경로
	 */
	@PostMapping(value = "/editok.do")
	public String editOk(LostPropertyDTO dto, HttpServletRequest req, MultipartFile doc) {
		
		LostPropertyDTO lost = service.editFile(dto, req, doc);

		int result = service.editLostProperty(lost);
		
		if (result == 1) {

	 		return "redirect:/admin/communication/lost-property/view.do";
	 
	 	} else {
	 
	 		return "redirect:/admin/communication/lost-property/edit.do";
	 
	 	}

	}
	
	/**
	 * 여러 개의 분실물을 삭제하는 메서드입니다.
	 *
	 * @param seqList 삭제할 분실물의 일련번호 배열
	 * @return 분실물 목록을 보여주는 뷰로의 리다이렉트 경로
	 */
	@PostMapping(value = "/del.do")
	public String del(String[] seqList) {

	    service.deleteLostProperty(seqList);

	    return "redirect:/admin/communication/lost-property/view.do";

	    
	}

}
