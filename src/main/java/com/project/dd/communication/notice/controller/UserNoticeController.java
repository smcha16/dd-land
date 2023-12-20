package com.project.dd.communication.notice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.communication.notice.domain.NoticeDTO;
import com.project.dd.communication.notice.service.NoticeService;

/**
 * 사용자용 공지사항 컨트롤러 클래스입니다.
 * 
 * @author sumin
 */
@Controller
@RequestMapping("/user/communication/notice")
public class UserNoticeController {
	
	@Autowired
	private NoticeService service;
	
	/**
	 * 공지사항 목록을 보여주는 메서드입니다.
	 *
	 * @param category 공지사항 카테고리
	 * @param word 검색어
	 * @param page 페이지 번호
	 * @param model Spring의 Model 객체
	 * @return 공지사항 목록을 보여주는 뷰의 경로
	 */
	@GetMapping(value = "/view.do")
	public String view(String category, String word, @RequestParam(defaultValue = "1") int page, Model model) {
		
		String solting = "user";
		
		String searchStatus = (category == null && word == null) || (category.equals("") && word.equals("")) ? "n" : "y";
		
		Map<String, String> map = service.paging(solting, searchStatus, category, word, page);

		List<NoticeDTO> list = service.getNoticeList(map);

		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("list", list);

		return "user/communication/notice/view";

	}
	
	/**
	 * 특정 공지사항의 상세 내용을 보여주는 메서드입니다.
	 *
	 * @param seq 공지사항의 일련번호
	 * @param model Spring의 Model 객체
	 * @return 특정 공지사항의 상세 내용을 보여주는 뷰의 경로
	 */
	@GetMapping(value = "/detail.do")
	public String detail(String seq, Model model) {
		
		NoticeDTO dto = service.getNotice(seq);
		
		model.addAttribute("dto", dto);

		return "user/communication/notice/detail";

	}

}
