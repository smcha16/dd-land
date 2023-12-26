package com.project.dd.activity.movie.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.activity.movie.domain.MovieDTO;

public interface MovieDAO {

	int getAdminPagingTotalPosts(Map<String, String> map);

	List<MovieDTO> getMovieList(Map<String, String> map);

	int addMovie(MovieDTO dto);

	MovieDTO getMovie(String seq);

	String getMovieImgFileName(String seq);

	int editMovie(MovieDTO dto);

	int delMovie(String seq);

}
