package com.project.dd.activity.movie.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.activity.movie.domain.MovieDTO;
import com.project.dd.activity.movieplay.domain.MoviePlayDTO;

public interface MovieDAO {

	List<MovieDTO> getMovieList(String date);

	MovieDTO getMovie(String seq);

	List<MoviePlayDTO> getMoviePlayList(String seq);

	List<MoviePlayDTO> getMoviePlay(String seq);

	int delMovie(String seq);

	int getTotalCount(String solting);

	List<MovieDTO> getMovieListAll(Map<String, String> map);

	int addMovie(MovieDTO dto);

	String getMovieImgFileName(String seq);

	int editMovie(MovieDTO dto);

	int checkMovieNameDuplication(MovieDTO dto);

}
