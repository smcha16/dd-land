package com.project.dd.activity.movieplay.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.activity.movie.domain.MovieDTO;
import com.project.dd.activity.movieplay.domain.MoviePlayDTO;
import com.project.dd.activity.movieplay.repository.MoviePlayDAO;
import com.project.dd.activity.theater.domain.TheaterDTO;

@Service
public class MoviePlayService {

	@Autowired
	MoviePlayDAO dao;
	
	public Map<String, String> paging(int page, String solting) {
		
		int pageSize = 0;
		
		//user or admin 노출 목록 개수 설정
		if (solting.equalsIgnoreCase("user")) {
			pageSize = 9;  //나타났으면 하는 개수(user)
			
		} else if (solting.equalsIgnoreCase("admin")) {
			pageSize = 10;  //나타났으면 하는 개수(admin)
		}
	      
		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;

		Map<String, String> map = new HashMap<String, String>();

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));

		int totalPosts = dao.getTotalCount(solting);
		int totalPages = (int) Math.ceil((double) totalPosts / pageSize);

		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));

		return map;
	}

	public List<MoviePlayDTO> getMoviePlayListAll(Map<String, String> map) {
		return dao.getMoviePlayListAll(map);
	}

	public int addMoviePlay(MoviePlayDTO dto) {
		return dao.addMoviePlay(dto);
	}

	public int delMoviePlay(String[] movieplay_seq) {
		
		int result = 0;
		
		for (String seq : movieplay_seq) {
			
			result += dao.delMoviePlay(seq);
		}
		
		return result;
	}

	public int editMoviePlay(MoviePlayDTO dto) {
		return dao.editMoviePlay(dto);
	}

	public MoviePlayDTO getMoviePlay(String seq) {
		return dao.getMoviePlay(seq);
	}

	public List<MovieDTO> getMovieList() {
		return dao.getMovieList();
	}

	public List<TheaterDTO> getTheaterList() {
		return dao.getTheaterList();
	}

}
