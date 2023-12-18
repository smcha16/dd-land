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

@Controller
@RequestMapping(value = "/user/activity/movie")
public class UserMoviePlayController {
	
	@Autowired
	private MovieService service;
	
	@GetMapping(value = "/view.do")
	public String view(Model model, @RequestParam(defaultValue = "sysdate") String date) {

		List<MovieDTO> list = service.getMovieList(date);
		
		model.addAttribute("list", list);
		model.addAttribute("date", date);
		
		return "user/activity/movie/view";
	}
	
	@GetMapping(value = "/detail.do")
	public String detail(Model model, String seq) {

		MovieDTO dto = service.getMovie(seq);
		
		List<MoviePlayDTO> plist = service.getMoviePlayList(seq);
		
		//dto에 plist 담기
		dto.setMoviePlayList(plist);
		
		//해당 영화의 상영 일정 가져오기
		List<MoviePlayDTO> moviePlayList = service.getMoviePlay(seq);
		
		model.addAttribute("dto", dto);
		model.addAttribute("moviePlayList", moviePlayList);
		
		return "user/activity/movie/detail";
	}

}

