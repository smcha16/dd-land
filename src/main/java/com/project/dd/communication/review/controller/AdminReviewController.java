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

import com.google.gson.Gson;
import com.project.dd.communication.review.domain.ReviewDTO;
import com.project.dd.communication.review.domain.ReviewImgDTO;
import com.project.dd.communication.review.service.ReviewService;

@Controller
@RequestMapping("/admin/communication/review")
public class AdminReviewController {
	
	@Autowired
	private ReviewService service;
	
	/* 목록 */
	
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
	
	/* 수정 */
	
	@GetMapping(value = "/edit.do")
	public String edit(String seq, Model model) {
		
		ReviewDTO dto = service.getReview(seq);
		
		model.addAttribute("dto", dto);
		
		return "admin/communication/review/edit";

	}
	
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
	
	/* 삭제 */
	
	@PostMapping(value = "/del.do")
	public String del(String[] seqList) {

		service.deleteFile(seqList);
		
	    service.deleteReview(seqList);

	    return "redirect:/admin/communication/review/view.do";
    
	}

}
