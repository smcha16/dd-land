package com.project.dd.activity.movieplay.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.activity.movie.domain.MovieDTO;
import com.project.dd.activity.movieplay.domain.MoviePlayDTO;
import com.project.dd.activity.theater.domain.TheaterDTO;

public interface MoviePlayMapper {

	int getTotalCount(String solting);

	List<MoviePlayDTO> getMoviePlayListAll(Map<String, String> map);

	int addMoviePlay(MoviePlayDTO dto);

	int delMoviePlay(String seq);

	int editMoviePlay(MoviePlayDTO dto);

	MoviePlayDTO getMoviePlayList(String seq);

	List<MovieDTO> getMovieList();

	List<TheaterDTO> getTheaterList();

}
