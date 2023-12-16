package com.project.dd.activity.moveplay.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.activity.moveplay.domain.MoviePlayDTO;

public interface MoviePlayMapper {

	int getTotalCount(String solting);

	List<MoviePlayDTO> getMoviePlayListAll(Map<String, String> map);

	int addMoviePlay(MoviePlayDTO dto);

	int delMoviePlay(String seq);

	int editMoviePlay(MoviePlayDTO dto);

	MoviePlayDTO getMoviePlayList(String seq);

}
