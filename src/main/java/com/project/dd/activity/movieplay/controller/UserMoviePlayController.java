package com.project.dd.activity.movieplay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.activity.movie.domain.MovieDTO;
import com.project.dd.activity.movie.service.MovieService;
import com.project.dd.activity.movieplay.domain.MoviePlayDTO;

/**
 * 
 * 영화 상영 조회(목록, 상세)를 담당하는 모든 회원 전용 컨트롤러 클래스입니다.
 * 
 * @author 박나래
 *
 */
@Controller
@RequestMapping(value = "/user/activity/movie")
public class UserMoviePlayController {
	
	@Autowired
	private MovieService msservice;
	
	@Autowired
	private MovieService mservice;
	
	/**
	 * 
	 * 당일 상영하는 영화의 목록을 불러오는 메서드입니다.
	 * 
	 * @param model 모델 객체
	 * @param date 날짜
	 * @return jsp 파일명
	 */
	@GetMapping(value = "/view.do")
	public String view(Model model, @RequestParam(defaultValue = "sysdate") String date) {

		List<MovieDTO> list = mservice.getMovieList(date);
		
		model.addAttribute("list", list);
		model.addAttribute("date", date);
		
		return "user/activity/movie/view";
	}
	
	/**
	 * 
	 * 선택한 특정 영화의 상세 내용을 불러오는 메서드입니다.
	 * 
	 * @param model 모델 객체
	 * @param seq 영화 번호
	 * @return jsp 파일명
	 */
	@GetMapping(value = "/detail.do")
	public String detail(Model model, String seq) {

		MovieDTO dto = mservice.getMovie(seq);
		
		List<MoviePlayDTO> plist = msservice.getMoviePlayList(seq);
		
		//dto에 plist 담기
		dto.setMoviePlayList(plist);
		
		//해당 영화의 상영 일정 가져오기
		List<MoviePlayDTO> moviePlayList = msservice.getMoviePlay(seq);
		
		model.addAttribute("dto", dto);
		model.addAttribute("moviePlayList", moviePlayList);
		
		return "user/activity/movie/detail";
	}

}

