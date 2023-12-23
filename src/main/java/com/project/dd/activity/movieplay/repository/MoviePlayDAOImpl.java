package com.project.dd.activity.movieplay.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.activity.movie.domain.MovieDTO;
import com.project.dd.activity.movieplay.domain.MoviePlayDTO;
import com.project.dd.activity.movieplay.mapper.MoviePlayMapper;
import com.project.dd.activity.theater.domain.TheaterDTO;

@Primary
@Repository
public class MoviePlayDAOImpl implements MoviePlayDAO{

	@Autowired
	MoviePlayMapper mapper;

	@Override
	public int getUserPagingTotalPosts(String date) {
		return mapper.getUserPagingTotalPosts(date);
	}
	
	@Override
	public List<MovieDTO> getMoviePlayList(Map<String, String> map) {
		return mapper.getMoviePlayList(map);
	}

	@Override
	public MovieDTO getMovie(String seq) {
		return mapper.getMovie(seq);
	}
	
	@Override
	public List<MoviePlayDTO> getMoviePlayListBySeq(Map<String, String> map) {
		return mapper.getMoviePlayListBySeq(map);
	}
	
	@Override
	public int getAdminPagingTotalPosts(Map<String, String> map) {
		return mapper.getAdminPagingTotalPosts(map);
	}
	
	@Override
	public List<MoviePlayDTO> getMoviePlayListAll(Map<String, String> map) {
		return mapper.getMoviePlayListAll(map);
	}
	
	@Override
	public List<MovieDTO> getMovieList() {
		return mapper.getMovieList();
	}
	
	@Override
	public List<TheaterDTO> getTheaterList() {
		return mapper.getTheaterList();
	}
	
	@Override
	public int addMoviePlay(MoviePlayDTO dto) {
		return mapper.addMoviePlay(dto);
	}
	
	@Override
	public MoviePlayDTO getMoviePlay(String seq) {
		return mapper.getMoviePlay(seq);
	}
	
	@Override
	public int editMoviePlay(MoviePlayDTO dto) {
		return mapper.editMoviePlay(dto);
	}
	
	@Override
	public int delMoviePlay(String seq) {
		return mapper.delMoviePlay(seq);
	}
	
}
