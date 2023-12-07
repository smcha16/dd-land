package com.project.dd.activity.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
