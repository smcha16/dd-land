package com.project.dd.activity.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.activity.movie.domain.MovieDTO;
import com.project.dd.activity.movie.service.MovieService;

@Controller
@RequestMapping(value = "/user/activity/movie")
public class UserMovieController {
	
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
		
		model.addAttribute("dto", dto);
		
		return "user/activity/movie/detail";
	}

}
