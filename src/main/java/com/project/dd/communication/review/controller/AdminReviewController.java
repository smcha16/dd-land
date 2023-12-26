package com.project.dd.communication.review.controller;

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

import com.project.dd.communication.review.domain.ReviewDTO;
import com.project.dd.communication.review.service.ReviewService;

/**
 * 관리자용 리뷰 컨트롤러 클래스입니다.
 * 
 * @author sumin
 */
@Controller
@RequestMapping("/admin/communication/review")
public class AdminReviewController {
	
	@Autowired
	private ReviewService service;
	
	/**
	 * 리뷰 목록을 보여주는 메서드입니다.
	 *
	 * @param word 검색어
	 * @param page 페이지 번호
	 * @param model Spring의 Model 객체
	 * @return 리뷰 목록을 보여주는 뷰의 경로
	 */
	@GetMapping(value = "/view.do")
	public String view(String word, @RequestParam(defaultValue = "1") int page, Model model) {
		
		String solting = "admin";
		
		String searchStatus = (word == null || word.equals("")) ? "n" : "y";
		
		Map<String, String> map = service.paging(solting, searchStatus, word, page);
		
		map.put("order", "n");
		
		List<ReviewDTO> list = service.getReviewList(map);
		
		String imgList = service.getReviewImgList(list);
		
		System.out.println(imgList);

		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("list", list);
		model.addAttribute("imgList", imgList);

		return "admin/communication/review/view";

	}
	
	/**
	 * 리뷰를 수정하는 폼을 보여주는 메서드입니다.
	 *
	 * @param seq 수정할 리뷰의 일련번호
	 * @param model Spring의 Model 객체
	 * @return 리뷰 수정 폼을 보여주는 뷰의 경로
	 */
	@GetMapping(value = "/edit.do")
	public String edit(String seq, Model model) {
		
		ReviewDTO dto = service.getReview(seq);
		
		model.addAttribute("dto", dto);
		
		return "admin/communication/review/edit";

	}
	
	/**
	 * 리뷰를 수정하는 메서드입니다.
	 *
	 * @param dto 수정할 리뷰의 DTO 객체
	 * @param seqList 수정할 파일의 일련번호 배열
	 * @param req HttpServletRequest 객체
	 * @param docs 첨부 파일 배열
	 * @return 리뷰 목록을 보여주는 뷰로의 리다이렉트 경로
	 */
	@PostMapping(value = "/editok.do")
	public String editOk(ReviewDTO dto, String[] seqList, HttpServletRequest req, MultipartFile[] docs) {
		
		service.editFile(seqList, req, docs);

		int result = service.editReview(dto);
		
		if (result == 1) {

	 		return "redirect:/admin/communication/review/view.do";
	 
	 	} else {
	 
	 		return "redirect:/admin/communication/review/edit.do";
	 
	 	}

	}
	
	/**
	 * 여러 개의 리뷰를 삭제하는 메서드입니다.
	 *
	 * @param seqList 삭제할 리뷰의 일련번호 배열
	 * @return 리뷰 목록을 보여주는 뷰로의 리다이렉트 경로
	 */
	@PostMapping(value = "/del.do")
	public String del(String[] seqList) {

		service.deleteFile(seqList);
		
	    service.deleteReview(seqList);

	    return "redirect:/admin/communication/review/view.do";
    
	}

}
