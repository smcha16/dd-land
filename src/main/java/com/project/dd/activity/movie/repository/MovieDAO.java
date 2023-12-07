package com.project.dd.activity.movie.repository;

import java.util.List;

import com.project.dd.activity.movie.domain.MovieDTO;

public interface MovieDAO {

	List<MovieDTO> getMovieList(String date);

	MovieDTO getMovie(String seq);

}
