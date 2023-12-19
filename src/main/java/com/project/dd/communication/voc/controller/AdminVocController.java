package com.project.dd.communication.voc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.communication.voc.domain.VocDTO;
import com.project.dd.communication.voc.service.VocService;

/**
 * 관리자용 칭찬/불편/건의 컨트롤러 클래스입니다.
 * 
 * @author sumin
 */
@Controller
@RequestMapping("/admin/communication/voc")
public class AdminVocController {
	
	@Autowired
	private VocService service;
	
	/**
	 * 칭찬/불편/건의 목록을 보여주는 메서드입니다.
	 *
	 * @param word 검색어
	 * @param page 페이지 번호
	 * @param model Spring의 Model 객체
	 * @return 칭찬/불편/건의 목록을 보여주는 뷰의 경로
	 */
	@GetMapping(value = "/view.do")
	public String view(String word, @RequestParam(defaultValue = "1") int page, Model model) {
		
		String searchStatus = (word == null || word.equals("")) ? "n" : "y";
		
		Map<String, String> map = service.paging(searchStatus, word, page);
		
		List<VocDTO> list = service.getVocList(map);
		
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("list", list);

		return "admin/communication/voc/view";

	}
	
	/**
	 * 칭찬/불편/건의에 답변을 등록하는 메서드입니다.
	 *
	 * @param dto 답변할 칭찬/불편/건의의 DTO 객체
	 * @return 칭찬/불편/건의 목록을 보여주는 뷰로의 리다이렉트 경로
	 */
	@PostMapping(value = "/answer.do")
	public String answer(VocDTO dto) {
		
		service.editAnswer(dto);

	 	return "redirect:/admin/communication/voc/view.do";

	}

}
