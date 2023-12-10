package com.project.dd.activity.movie.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.activity.movie.domain.MovieDTO;
import com.project.dd.activity.movie.domain.MoviePlayDTO;
import com.project.dd.activity.movie.mapper.MovieMapper;

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

	//영화 삭제
	// - 1. 배열 돌면서 seq 뽑아내기
	// - 2. 해당하는 seq의 레코드 UPDATE (상영종료)
	@Override
	public int delMovie(String[] movie_seq) {
		
		int result = 0;
		
		//1. 배열 돌면서 seq 뽑아내기
		for (String seq : movie_seq) {
			
			//2. 해당하는 seq의 레코드 UPDATE (상영종료)
			result += mapper.delMovie(seq);
		}
		
		return result;
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
	
	
}
