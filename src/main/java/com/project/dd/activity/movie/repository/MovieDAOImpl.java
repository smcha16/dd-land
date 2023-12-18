package com.project.dd.activity.movie.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.activity.movie.domain.MovieDTO;
import com.project.dd.activity.movie.mapper.MovieMapper;
import com.project.dd.activity.movieplay.domain.MoviePlayDTO;

@Primary
@Repository
public class MovieDAOImpl implements MovieDAO {

	@Autowired
	MovieMapper mapper;

	@Override
	public List<MovieDTO> getMovieList(String date) {
		return mapper.getMovieList(date);
	}

	@Override
	public MovieDTO getMovie(String seq) {
		return mapper.getMovie(seq);
	}

	@Override
	public List<MoviePlayDTO> getMoviePlayList(String seq) {
		return mapper.getMoviePlayList(seq);
	}

	@Override
	public List<MoviePlayDTO> getMoviePlay(String seq) {
		return mapper.getMoviePlay(seq);
	}

	@Override
	public int delMovie(String seq) {
		return mapper.delMovie(seq);
	}

	@Override
	public int getTotalCount(String solting) {
		return mapper.getTotalCount(solting);
	}

	@Override
	public List<MovieDTO> getMovieListAll(Map<String, String> map) {
		return mapper.getMovieListAll(map);
	}

	@Override
	public int addMovie(MovieDTO dto) {
		return mapper.addMovie(dto);
	}

	@Override
	public String getMovieImgFileName(String seq) {
		return mapper.getMovieImgFileName(seq);
	}

	@Override
	public int editMovie(MovieDTO dto) {
		return mapper.editMovie(dto);
	}

	@Override
	public int checkMovieNameDuplication(MovieDTO dto) {
		return mapper.checkMovieNameDuplication(dto);
	}
	
	
}
