package com.project.dd.activity.movie.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.activity.movie.domain.MovieDTO;
import com.project.dd.activity.movie.domain.MoviePlayDTO;
import com.project.dd.activity.movie.repository.MovieDAO;

@Service
public class MovieService {

	@Autowired
	MovieDAO dao;

	public List<MovieDTO> getMovieList(String date) {
		return dao.getMovieList(date);
	}

	public MovieDTO getMovie(String seq) {
		return dao.getMovie(seq);
	}

	public List<MoviePlayDTO> getMoviePlayList(String seq) {
		return dao.getMoviePlayList(seq);
	}

	public List<MoviePlayDTO> getMoviePlay(String seq) {
		return dao.getMoviePlay(seq);
	}

	public int delMovie(String seq) {
		return dao.delMovie(seq);
	}

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

	public List<MovieDTO> getMovieListAll(Map<String, String> map) {
		return dao.getMovieListAll(map);
	}

	public int addMovie(MovieDTO dto, MultipartFile imgs, HttpServletRequest req) {
		return dao.addMovie(dto);
	}
}
