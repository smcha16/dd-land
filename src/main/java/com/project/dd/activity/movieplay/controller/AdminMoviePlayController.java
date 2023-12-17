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
import com.project.dd.activity.movie.service.MovieService;
import com.project.dd.activity.movieplay.domain.MoviePlayDTO;
import com.project.dd.activity.movieplay.service.MoviePlayService;
import com.project.dd.activity.theater.domain.TheaterDTO;
import com.project.dd.activity.theater.service.TheaterService;

@Controller
@RequestMapping(value = "/admin/activity/movieplay")
public class AdminMoviePlayController {

	@Autowired
	private MoviePlayService service;
	
	@GetMapping(value = "/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {

		//페이징
		String solting = "admin";
		Map<String, String> map = service.paging(page, solting);
		
		//MoviePlay 목록 전체
		List<MoviePlayDTO> list = service.getMoviePlayListAll(map);
		
		//페이징 전달
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		
		//MoviePlay 목록 전달
		model.addAttribute("list", list);
		
		return "admin/activity/movieplay/view";
	}
	
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
	
	@PostMapping(value = "/addok.do")
	public String addok(Model model, MoviePlayDTO dto) {

		int result = service.addMoviePlay(dto);
		
		if (result > 0) {
			return "redirect:/admin/activity/movieplay/view.do";
		} else {
			return "redirect:/admin/activity/movieplay/add.do";
		}
		
	}
	
	@GetMapping(value = "/edit.do")
	public String edit(Model model, String seq) {

		MoviePlayDTO dto = service.getMoviePlay(seq);
		
		model.addAttribute("dto", dto);
		
		return "admin/activity/movieplay/edit";
	}
	
	@PostMapping(value = "/editok.do")
	public String editok(Model model, MoviePlayDTO dto) {

		int result = service.editMoviePlay(dto);
		
		if (result > 0) {
			return "redirect:/admin/activity/movieplay/view.do";
		} else {
			return "redirect:/admin/activity/movieplay/edit.do";
		}
		
	}
	
	@PostMapping(value = "/del.do")
	public String del(Model model, String[] movieplay_seq) {

		int result = service.delMoviePlay(movieplay_seq);
		
		return "redirect:/admin/activity/movieplay/view.do";
		
	}
}
