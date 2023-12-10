package com.project.dd.activity.movie.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

	//영화 삭제
	// - 1. 배열 돌면서 seq 뽑아내기
	// - 2. 해당하는 seq의 레코드 UPDATE (상영종료)
	public int delMovie(String[] movie_seq) {
		
		int result = 0;
		
		//1. 배열 돌면서 seq 뽑아내기
		for (String seq : movie_seq) {
			
			//2. 해당하는 seq의 레코드 UPDATE (상영종료)
			result += dao.delMovie(seq);
		}
		
		return result;
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
		
		try {
			
			UUID uuid = UUID.randomUUID();
			
			String filename = uuid + "_" + imgs.getOriginalFilename();
			
			imgs.transferTo(new File(req.getRealPath("/resources/files/activity/attraction") + "\\" + filename));
			
			dto.setImg(filename);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dao.addMovie(dto);
	}
}
