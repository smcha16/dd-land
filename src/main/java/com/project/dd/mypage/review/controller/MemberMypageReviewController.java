package com.project.dd.mypage.review.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.login.domain.CustomUser;
import com.project.dd.mypage.review.domain.ReviewDTO;
import com.project.dd.mypage.review.service.MypageReviewService;

@Controller
@RequestMapping("/member/mypage/review")
public class MemberMypageReviewController {

	@Autowired
	private MypageReviewService service;

	@GetMapping(value = "/view.do")
	public String view(Model model, Authentication auth, @RequestParam(defaultValue = "1") int page) {

		Map<String, String> map = service.paging(page); // 페이징

		String email = ((CustomUser) auth.getPrincipal()).getDto().getEmail();

		map.put("email", email);
		
		List<ReviewDTO> list = service.list(map);
		
		//System.out.println(list.toString());

		model.addAttribute("list", list);
		model.addAttribute("email", email);
		model.addAttribute("currentPage", page); // 페이징
		model.addAttribute("map", map); // 페이징

		return "mypage/review/view";
	}

	@PostMapping(value = "/delete.do")
	public String delete(Model model, String[] selectedReview) {

		int imgResult = service.imgDelete(selectedReview);
		int result = service.delete(selectedReview);

		if (result == 1 || imgResult == 1) {

			return "redirect:/member/mypage/review/view.do";

		} else {

			System.out.println("review delete error");

		}

		return "redirect:/member/mypage/review/view.do";

	}

	@PostMapping(value = "/add.do")
	public String add(Model model, String selectedReview) {

		model.addAttribute("user_book_seq", selectedReview);
		
		return "mypage/review/add";
	}

	@PostMapping(value = "/addok.do")
	public String addok(Model model, ReviewDTO dto, MultipartFile[] imgs, HttpServletRequest req) {

		int result = service.add(dto, imgs, req);
		
		System.out.println(imgs);
		System.out.println(imgs[0].isEmpty());

		if (result > 0) {

			return "redirect:/member/mypage/review/view.do";

		} else {

			System.out.println("review add error");

		}

		return "redirect:/member/mypage/review/view.do";
	}

	@GetMapping(value = "/edit.do")
	public String edit(Model model, String seq) {
		
		 ReviewDTO dto = service.get(seq);
		  
		 model.addAttribute("dto", dto);
		 
		return "mypage/review/edit";
	}

	@PostMapping(value = "/editok.do")
	public String editok(Model model, ReviewDTO dto) {
		
		System.out.println(dto);
		
		int result = service.edit(dto);

		if (result == 1) {

			return "redirect:/member/mypage/review/view.do";

		} else {

			System.out.println("review edit error");
			return "redirect:/member/mypage/review/edit.do";

		}

	}

}
