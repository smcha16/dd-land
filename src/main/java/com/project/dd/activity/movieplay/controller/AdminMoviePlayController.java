package com.project.dd.activity.movieplay.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.activity.movie.domain.MovieDTO;
import com.project.dd.activity.movieplay.domain.MoviePlayDTO;
import com.project.dd.activity.movieplay.service.MoviePlayService;
import com.project.dd.activity.theater.domain.TheaterDTO;

/**
 * 
 * 영화 상영 관리(조회/추가/수정/삭제)를 담당하는 관리자 전용 컨트롤러 클래스입니다.
 * 
 * @author 박나래
 *
 */
@Controller
@RequestMapping(value = "/admin/activity/movieplay")
public class AdminMoviePlayController {

	@Autowired
	private MoviePlayService service;
	
	/**
	 * 
	 * 관리자용 영화 상영 목록 전체를 조회할 수 있는 view 메서드입니다.
	 * 
	 * @param word 검색어(영화명/영화관명)
	 * @param page 페이지
	 * @param model 모델 객체
	 * @return 호출할 jsp 파일명
	 */
	@GetMapping(value = "/view.do")
	public String view(String word, @RequestParam(defaultValue = "1") int page, Model model) {

		String searchStatus = (word == null || word.equals("")) ? "n" : "y";
		
		//Admin 전용 페이징
		Map<String, String> map = service.adminPaging(searchStatus, word, page);
		
		//MoviePlay 목록 전체
		List<MoviePlayDTO> list = service.getMoviePlayListAll(map);
		
		//페이징 전달
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		
		//MoviePlay 목록 전달
		model.addAttribute("list", list);
		
		return "admin/activity/movieplay/view";
	}
	
	/**
	 * 
	 * 영화 상영을 추가하는 메서드입니다.
	 * 
	 * @param model 모델 객체
	 * @return 호출할 jsp 파일명
	 */
	@GetMapping(value = "/add.do")
	public String add(Model model) {

		//영화 목록
		List<MovieDTO> mlist = service.getMovieList();
		
		//영화관 목록
		List<TheaterDTO> tlist = service.getTheaterList();
		
		model.addAttribute("mlist", mlist);
		model.addAttribute("tlist", tlist);
		
		return "admin/activity/movieplay/add";
	}
	
	/**
	 * 
	 * 추가한 영화 상영을 DB에서 처리하고 처리 결과에 따라 이동할 페이지를 호출하는 메서드입니다.
	 * 
	 * @param model 모델 객체
	 * @param dto 영화상영 DTO 객체
	 * @return 이동할 페이지 주소
	 */
	@PostMapping(value = "/addok.do")
	public String addok(Model model, MoviePlayDTO dto) {

		int result = service.addMoviePlay(dto);
		
		if (result > 0) {
			return "redirect:/admin/activity/movieplay/view.do";
		} else {
			return "redirect:/admin/activity/movieplay/add.do";
		}
		
	}
	
	/**
	 * 
	 * 영화 상영을 수정하는 메서드입니다.
	 * 
	 * @param model 모델 객체
	 * @param seq 영화상영번호
	 * @return 호출할 jsp 파일명
	 */
	@GetMapping(value = "/edit.do")
	public String edit(Model model, String seq) {

		MoviePlayDTO dto = service.getMoviePlay(seq);
		
		model.addAttribute("dto", dto);
		
		return "admin/activity/movieplay/edit";
	}
	
	/**
	 * 
	 * 수정한 영화 상영을 DB에서 처리하고 처리 결과에 따라 이동할 페이지를 호출하는 메서드입니다.
	 * 
	 * @param model 모델 객체
	 * @param dto 영화상영 DTO 객체
	 * @return 이동할 페이지 주소
	 */
	@PostMapping(value = "/editok.do")
	public String editok(Model model, MoviePlayDTO dto) {

		int result = service.editMoviePlay(dto);
		
		if (result > 0) {
			return "redirect:/admin/activity/movieplay/view.do";
		} else {
			return "redirect:/admin/activity/movieplay/edit.do";
		}
		
	}
	
	/**
	 * 
	 * 삭제할 영화 상영을 DB에서 처리하고 처리 결과에 따라 이동할 페이지를 호출하는 메서드입니다.
	 * 
	 * @param model 모델 객체
	 * @param movieplay_seq 영화상영번호
	 * @return 이동할 페이지 주소
	 */
	@PostMapping(value = "/del.do")
	public String del(Model model, String[] movieplay_seq) {

		int result = service.delMoviePlay(movieplay_seq);
		
		return "redirect:/admin/activity/movieplay/view.do";
		
	}
}
