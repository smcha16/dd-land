package com.project.dd.activity.movieplay.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.activity.movie.domain.MovieDTO;
import com.project.dd.activity.movieplay.domain.MoviePlayDTO;
import com.project.dd.activity.theater.domain.TheaterDTO;

public interface MoviePlayMapper {

	int getUserPagingTotalPosts(String date);
	
	List<MovieDTO> getMoviePlayList(Map<String, String> map);

	MovieDTO getMovie(String seq);
	
	List<MoviePlayDTO> getMoviePlayListBySeqDate(Map<String, String> map);
	
	MoviePlayDTO getMoviePlayBySeqDate(Map<String, String> map);

	int getAdminPagingTotalPosts(Map<String, String> map);
	
	List<MoviePlayDTO> getMoviePlayListAll(Map<String, String> map);
	
	List<MovieDTO> getMovieList();
	
	List<TheaterDTO> getTheaterList();
	
	int addMoviePlay(MoviePlayDTO dto);
	
	MoviePlayDTO getMoviePlay(String seq);
	
	int editMoviePlay(MoviePlayDTO dto);

	int delMoviePlay(String seq);

}
