package com.project.dd.communication.notice.controller;

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
import com.project.dd.communication.notice.service.NoticeService;

/**
 * 관리자용 공지사항 컨트롤러 클래스입니다.
 * 
 * @author sumin
 */
@Controller
@RequestMapping("/admin/communication/notice")
public class AdminNoticeController {
	
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
		
		String solting = "admin";
		
		String searchStatus = (word == null || word.equals("")) ? "n" : "y";

		Map<String, String> map = service.paging(solting, searchStatus, category, word, page);

		List<NoticeDTO> list = service.getNoticeList(map);

		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("list", list);

		return "admin/communication/notice/view";

	}
	
	/**
	 * 공지사항을 추가하는 폼을 보여주는 메서드입니다.
	 *
	 * @return 공지사항 추가 폼을 보여주는 뷰의 경로
	 */
	@GetMapping(value = "/add.do")
	public String add() {

		return "admin/communication/notice/add";

	}
	
	/**
	 * 공지사항을 추가하는 메서드입니다.
	 *
	 * @param dto 추가할 공지사항의 DTO 객체
	 * @param req HttpServletRequest 객체
	 * @param doc 첨부 파일
	 * @return 공지사항 목록을 보여주는 뷰로의 리다이렉트 경로
	 */
	@PostMapping(value = "/addok.do")
	public String addOk(NoticeDTO dto, HttpServletRequest req, MultipartFile doc) {
		
		NoticeDTO notice = service.addFile(dto, req, doc);

		int result = service.addNotice(notice);
		
		if (result == 1) {

	 		return "redirect:/admin/communication/notice/view.do";
	 
	 	} else {
	 
	 		return "redirect:/admin/communication/notice/add.do";
	 
	 	}

	}
	
	/**
	 * 공지사항을 수정하는 폼을 보여주는 메서드입니다.
	 *
	 * @param seq 공지사항의 일련번호
	 * @param model Spring의 Model 객체
	 * @return 공지사항 수정 폼을 보여주는 뷰의 경로
	 */
	@GetMapping(value = "/edit.do")
	public String edit(String seq, Model model) {
		
		NoticeDTO dto = service.getNotice(seq);
		
		model.addAttribute("dto", dto);

		return "admin/communication/notice/edit";

	}
	
	/**
	 * 공지사항을 수정하는 메서드입니다.
	 *
	 * @param dto 수정할 공지사항의 DTO 객체
	 * @param req HttpServletRequest 객체
	 * @param doc 첨부 파일
	 * @return 공지사항 목록을 보여주는 뷰로의 리다이렉트 경로
	 */
	@PostMapping(value = "/editok.do")
	public String editOk(NoticeDTO dto, HttpServletRequest req, MultipartFile doc) {
		
		NoticeDTO notice = service.editFile(dto, req, doc);

		int result = service.editNotice(notice);
		
		if (result == 1) {

	 		return "redirect:/admin/communication/notice/view.do";
	 
	 	} else {
	 
	 		return "redirect:/admin/communication/notice/edit.do";
	 
	 	}

	}
	
	/**
	 * 여러 개의 공지사항을 삭제하는 메서드입니다.
	 *
	 * @param seqList 삭제할 공지사항의 일련번호 배열
	 * @return 공지사항 목록을 보여주는 뷰로의 리다이렉트 경로
	 */
	@PostMapping(value = "/del.do")
	public String del(String[] seqList) {

	    service.deleteNotice(seqList);

	    return "redirect:/admin/communication/notice/view.do";

	    
	}

}
