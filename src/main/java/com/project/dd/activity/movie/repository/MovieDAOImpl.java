package com.project.dd.activity.movie.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.activity.movie.domain.MovieDTO;
import com.project.dd.activity.movie.domain.MoviePlayDTO;
import com.project.dd.activity.movie.mapper.MovieMapper;

@Primary
@Repository
public class MovieDAOImpl implements MovieDAO {

	@Autowired
	MovieMapper mapper;

	@Override
	public List<MovieDTO> getMovieList(String date) {
		return mapper.getMovieList(date);
	}

	@Override
	public MovieDTO getMovie(String seq) {
		return mapper.getMovie(seq);
	}

	@Override
	public List<MoviePlayDTO> getMoviePlayList(String seq) {
		return mapper.getMoviePlayList(seq);
	}
	
	
}
