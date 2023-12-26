package com.project.dd.activity.movie.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.activity.movie.domain.MovieDTO;
import com.project.dd.activity.movie.mapper.MovieMapper;

@Primary
@Repository
public class MovieDAOImpl implements MovieDAO {

	@Autowired
	MovieMapper mapper;

	@Override
	public int getAdminPagingTotalPosts(Map<String, String> map) {
		return mapper.getAdminPagingTotalPosts(map);
	}

	@Override
	public List<MovieDTO> getMovieList(Map<String, String> map) {
		return mapper.getMovieList(map);
	}

	@Override
	public int addMovie(MovieDTO dto) {
		return mapper.addMovie(dto);
	}

	@Override
	public MovieDTO getMovie(String seq) {
		return mapper.getMovie(seq);
	}

	@Override
	public String getMovieImgFileName(String seq) {
		return mapper.getMovieImgFileName(seq);
	}

	@Override
	public int editMovie(MovieDTO dto) {
		return mapper.editMovie(dto);
	}

	@Override
	public int delMovie(String seq) {
		return mapper.delMovie(seq);
	}
	
	
}
