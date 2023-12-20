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

	/**
	 * 회원의 리뷰 내역을 조회하는 메서드입니다.
	 *
	 * @param model        데이터를 뷰에 전달하기 위한 Model 객체
	 * @param auth         현재 사용자의 인증 정보를 담은 Authentication 객체
	 * @param page         현재 페이지 번호
	 * @return             회원의 리뷰 내역을 보여주는 뷰 페이지
	 */
	@GetMapping(value = "/view.do")
	public String view(Model model, Authentication auth, @RequestParam(defaultValue = "1") int page) {

		Map<String, String> map = service.paging(page); // 페이징

		String email = ((CustomUser) auth.getPrincipal()).getDto().getEmail();

		map.put("email", email);
		
		List<ReviewDTO> list = service.list(map);
		
		String imgList = service.getReviewImgList(list);

		System.out.println(list.toString());

		model.addAttribute("list", list);
		model.addAttribute("email", email);
		model.addAttribute("currentPage", page); // 페이징
		model.addAttribute("map", map); // 페이징
		model.addAttribute("imgList", imgList);

		return "mypage/review/view";
	}

	/**
     * 회원의 리뷰를 삭제합니다.
     *
     * @param model            데이터를 뷰에 전달하기 위한 Model 객체
     * @param selectedReview   삭제할 리뷰의 ID 배열
     * @return                 리뷰 삭제 후 리뷰 목록 페이지로 리다이렉트
     */
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

	/**
     * 회원이 작성한 리뷰를 추가하는 페이지로 이동합니다.
     *
     * @param model             데이터를 뷰에 전달하기 위한 Model 객체
     * @param selectedReview    추가할 리뷰의 ID
     * @return                  리뷰 추가 페이지로 이동
     */
	@PostMapping(value = "/add.do")
	public String add(Model model, String selectedReview) {

		model.addAttribute("user_book_seq", selectedReview);
		
		return "mypage/review/add";
	}

	 /**
     * 리뷰를 추가합니다.
     *
     * @param model            데이터를 뷰에 전달하기 위한 Model 객체
     * @param dto              추가할 리뷰 정보를 담은 DTO 객체
     * @param imgs             리뷰에 첨부할 이미지 파일 배열
     * @param req              HTTP 요청 정보를 담은 HttpServletRequest 객체
     * @return                 리뷰 추가 후 리뷰 목록 페이지로 리다이렉트
     */
	@PostMapping(value = "/addok.do")
	public String addok(Model model, ReviewDTO dto, MultipartFile[] imgs, HttpServletRequest req) {

		int result = service.add(dto, imgs, req);
		
		//System.out.println(imgs);
		//System.out.println(imgs[0].isEmpty());

		if (result > 0) {

			return "redirect:/member/mypage/review/view.do";

		} else {

			System.out.println("review add error");

		}

		return "redirect:/member/mypage/review/view.do";
	}

	/**
     * 리뷰 수정 페이지로 이동합니다.
     *
     * @param model            데이터를 뷰에 전달하기 위한 Model 객체
     * @param seq              수정할 리뷰의 ID
     * @return                 리뷰 수정 페이지로 이동
     */
	@GetMapping(value = "/edit.do")
	public String edit(Model model, String seq) {
		
		 ReviewDTO dto = service.get(seq);
		  
		 model.addAttribute("dto", dto);
		 
		return "mypage/review/edit";
	}

	/**
     * 수정된 리뷰를 저장합니다.
     *
     * @param model            데이터를 뷰에 전달하기 위한 Model 객체
     * @param dto              수정된 리뷰 정보를 담은 DTO 객체
     * @return                 리뷰 수정 후 리뷰 목록 페이지로 리다이렉트
     */
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
