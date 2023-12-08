package com.project.dd.activity.movie.repository;

import java.util.List;

import com.project.dd.activity.movie.domain.MovieDTO;
import com.project.dd.activity.movie.domain.MoviePlayDTO;

public interface MovieDAO {

	List<MovieDTO> getMovieList(String date);

	MovieDTO getMovie(String seq);

	List<MoviePlayDTO> getMoviePlayList(String seq);

	List<MoviePlayDTO> getMoviePlay(String seq);

}