package com.project.dd.activity.moveplay.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.activity.moveplay.domain.MoviePlayDTO;
import com.project.dd.activity.moveplay.mapper.MoviePlayMapper;

@Primary
@Repository
public class MoviePlayDAOImpl implements MoviePlayDAO{

	@Autowired
	MoviePlayMapper mapper;

	@Override
	public int getTotalCount(String solting) {
		return mapper.getTotalCount(solting);
	}

	@Override
	public List<MoviePlayDTO> getMoviePlayListAll(Map<String, String> map) {
		return mapper.getMoviePlayListAll(map);
	}

	@Override
	public int addMoviePlay(MoviePlayDTO dto) {
		return mapper.addMoviePlay(dto);
	}

	@Override
	public int delMoviePlay(String seq) {
		return mapper.delMoviePlay(seq);
	}

	@Override
	public int editMoviePlay(MoviePlayDTO dto) {
		return mapper.editMoviePlay(dto);
	}

	@Override
	public MoviePlayDTO getMoviePlay(String seq) {
		return mapper.getMoviePlayList(seq);
	}

	
}
