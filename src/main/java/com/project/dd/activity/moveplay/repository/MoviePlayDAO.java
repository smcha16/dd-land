package com.project.dd.activity.moveplay.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.activity.moveplay.domain.MoviePlayDTO;

public interface MoviePlayDAO {

	int getTotalCount(String solting);

	List<MoviePlayDTO> getMoviePlayListAll(Map<String, String> map);

	int addMoviePlay(MoviePlayDTO dto);

	int delMoviePlay(String seq);

	int editMoviePlay(MoviePlayDTO dto);

	MoviePlayDTO getMoviePlay(String seq);

}
