package com.project.dd.communication.faq.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.communication.faq.domain.FaqDTO;
import com.project.dd.communication.faq.service.FaqService;

/**
 * 관리자용 FAQ 컨트롤러 클래스입니다.
 * 
 * @author sumin
 */
@Controller
@RequestMapping("/admin/communication/faq")
public class AdminFaqController {
	
	@Autowired
	private FaqService service;
	
	/**
	 * FAQ 목록을 보여주는 메서드입니다.
	 *
	 * @param word 검색어
	 * @param page 페이지 번호
	 * @param model Spring의 Model 객체
	 * @return FAQ 목록을 보여주는 뷰의 경로
	 */
	@GetMapping(value = "/view.do")
	public String view(String word, @RequestParam(defaultValue = "1") int page, Model model) {
		
		String type = "없음";
		
		String searchStatus = (word == null || word.equals("")) ? "n" : "y";

		Map<String, String> map = service.paging(type, searchStatus, word, page);
		
		List<FaqDTO> list = service.getFaqList(map);

		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("list", list);

		return "admin/communication/faq/view";

	}
	
	/**
	 * FAQ를 추가하는 폼을 보여주는 메서드입니다.
	 *
	 * @return FAQ 추가 폼을 보여주는 뷰의 경로
	 */
	@GetMapping(value = "/add.do")
	public String add() {

		return "admin/communication/faq/add";

	}
	
	/**
	 * FAQ를 추가하는 메서드입니다.
	 *
	 * @param dto 추가할 FAQ의 DTO 객체
	 * @return FAQ 목록을 보여주는 뷰로의 리다이렉트 경로 또는 FAQ 추가 폼의 뷰로의 리다이렉트 경로
	 */
	@PostMapping(value = "/addok.do")
	public String addOk(FaqDTO dto) {

		int result = service.addFaq(dto);
		
		if (result == 1) {

	 		return "redirect:/admin/communication/faq/view.do";
	 
	 	} else {
	 
	 		return "redirect:/admin/communication/faq/add.do";
	 
	 	}

	}
	
	/**
	 * FAQ를 수정하는 폼을 보여주는 메서드입니다.
	 *
	 * @param seq 수정할 FAQ의 일련번호
	 * @param model Spring의 Model 객체
	 * @return FAQ 수정 폼을 보여주는 뷰의 경로
	 */
	@GetMapping(value = "/edit.do")
	public String edit(String seq, Model model) {
		
		FaqDTO dto = service.getFaq(seq);
		
		model.addAttribute("dto", dto);

		return "admin/communication/faq/edit";

	}
	
	/**
	 * FAQ를 수정하는 메서드입니다.
	 *
	 * @param dto 수정할 FAQ의 DTO 객체
	 * @return FAQ 목록을 보여주는 뷰로의 리다이렉트 경로 또는 FAQ 수정 폼의 뷰로의 리다이렉트 경로
	 */
	@PostMapping(value = "/editok.do")
	public String editOk(FaqDTO dto) {

		int result = service.editFaq(dto);
		
		if (result == 1) {

	 		return "redirect:/admin/communication/faq/view.do";
	 
	 	} else {
	 
	 		return "redirect:/admin/communication/faq/edit.do";
	 
	 	}

	}

	/**
	 * 여러 개의 FAQ를 삭제하는 메서드입니다.
	 *
	 * @param seqList 삭제할 FAQ의 일련번호 배열
	 * @return FAQ 목록을 보여주는 뷰로의 리다이렉트 경로
	 */
	@PostMapping(value = "/del.do")
	public String del(String[] seqList) {

	    service.deleteFaq(seqList);

	    return "redirect:/admin/communication/faq/view.do";
	    
	}

}
