package com.project.dd.activity.movieplay.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.activity.movie.domain.MovieDTO;
import com.project.dd.activity.movieplay.domain.MoviePlayDTO;
import com.project.dd.activity.movieplay.repository.MoviePlayDAO;
import com.project.dd.activity.theater.domain.TheaterDTO;

/**
 * 
 * 영화 상영 페이지의 비즈니스 로직을 담당하는 Service 클래스입니다.
 * 
 * @author 박나래
 *
 */
@Service
public class MoviePlayService {

	@Autowired
	MoviePlayDAO dao;

	/**
	 * 
	 * 사용자 페이지의 페이지 번호를 출력하기 위해 페이지당 노출 목록 개수 설정 및 검색 결과값의 개수를 조회하는 메서드입니다.
	 * 
	 * @param page 페이지 번호
	 * @param date 상영 일자
	 * @return 위의 정보가 담긴 map 객체
	 */
	public Map<String, String> userPaging(int page, String date) {
	
		//User 페이지 노출 목록 개수 설정
		int pageSize = 9;
		
		//페이지별로 가져올 index 번호
		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;
		
		//페이징용 Map 생성
		Map<String, String> map = new HashMap<String, String>();
	
		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));
		
		int totalPosts = dao.getUserPagingTotalPosts(date);
		int totalPages = (int) Math.ceil((double) totalPosts / pageSize);
		
		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));
		
		return map;
		
	}

	/**
	 * 
	 * 사용자 페이지의 영화 상영 목록을 가져오는 메서드입니다.
	 * 
	 * @param map 페이징을 위한 Map 객체
	 * @param date 상영 일자
	 * @return MovieDTO 객체 List
	 */
	public List<MovieDTO> getMoviePlayList(Map<String, String> map, String date) {
		
		map.put("date", date);
		
		return dao.getMoviePlayList(map);
	}

	/**
	 * 
	 * 특정 영화의 상세 정보를 가져오는 메서드입니다. 
	 * 
	 * @param seq 영화 번호
	 * @return MovieDTO 객체
	 */
	public MovieDTO getMovie(String seq) {
		return dao.getMovie(seq);
	}

	/**
	 * 
	 * 특정일의 특정 영화의 상영 목록을 가져오는 메서드입니다.
	 * 
	 * @param seq 영화 번호
	 * @param date 상영 일자
	 * @return MoviePlayDTO 객체 List
	 */
	public List<MoviePlayDTO> getMoviePlayListBySeqDate(String seq, String date) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("seq", seq);
		map.put("date", date);
		
		return dao.getMoviePlayListBySeqDate(map);
	}

	/**
	 * 
	 * 특정일의 특정 영화 상영 정보를 가져오는 메서드입니다.
	 * 
	 * @param map Map 객체
	 * @return MoviePlayDTO 객체
	 */
	public MoviePlayDTO getMoviePlayBySeqDate(Map<String, String> map) {
		
		return dao.getMoviePlayBySeqDate(map);
	}

	/**
	 * 
	 * 관리자 페이지의 페이지 번호를 출력하기 위해 페이지당 노출 목록 개수 설정 및 검색 결과값의 개수를 조회하는 메서드입니다.
	 * 
	 * @param searchStatus 검색여부
	 * @param word 검색어
	 * @param page 페이지 번호
	 * @return 위의 정보가 담긴 map 객체
	 */
	public Map<String, String> adminPaging(String searchStatus, String word, int page) {
		
		//Admin 페이지 노출 목록 개수 설정
		int pageSize = 10;
		
		//페이지별로 가져올 index 번호
		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;

		//페이징용 Map 생성
		Map<String, String> map = new HashMap<String, String>();

		map.put("searchStatus", searchStatus);
		map.put("word", word);
		
		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));

		int totalPosts = dao.getAdminPagingTotalPosts(map);
		int totalPages = (int) Math.ceil((double) totalPosts / pageSize);

		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));

		return map;
	}

	/**
	 * 
	 * 전체 영화 상영 목록을 가져오는 메서드입니다.
	 * 
	 * @param map 페이징을 위한 map 객체
	 * @return MoviePlayDTO 객체 List
	 */
	public List<MoviePlayDTO> getMoviePlayListAll(Map<String, String> map) {
		return dao.getMoviePlayListAll(map);
	}

	/**
	 * 
	 * 전체 영화 목록을 가져오는 메서드입니다.
	 * 
	 * @return MovieDTO 객체 List
	 */
	public List<MovieDTO> getMovieList() {
		return dao.getMovieList();
	}

	/**
	 * 
	 * 전체 영화관 목록을 가져오는 메서드입니다.
	 * 
	 * @return TheaterDTO 객체 List
	 */
	public List<TheaterDTO> getTheaterList() {
		return dao.getTheaterList();
	}

	/**
	 * 
	 * 영화 상영을 추가하는 메서드입니다.
	 * 
	 * @param dto MoviePlayDTO 객체
	 * @return 추가된 행의 개수
	 */
	public int addMoviePlay(MoviePlayDTO dto) {
		return dao.addMoviePlay(dto);
	}

	/**
	 * 
	 * 특정 영화 상영 정보를 가져오는 메서드입니다.
	 * 
	 * @param seq 영화 상영 번호
	 * @return MoviePlayDTO 객체
	 */
	public MoviePlayDTO getMoviePlay(String seq) {
		return dao.getMoviePlay(seq);
	}

	/**
	 * 
	 * 영화 상영을 수정하는 메서드입니다.
	 * 
	 * @param dto MoviePlayDTO 객체
	 * @return 수정된 행의 개수
	 */
	public int editMoviePlay(MoviePlayDTO dto) {
		return dao.editMoviePlay(dto);
	}

	/**
	 * 
	 * 영화 상영을 삭제하는 메서드입니다.
	 * 
	 * @param movieplay_seq 영화 상영 번호
	 * @return 삭제된 행의 개수
	 */
	public int delMoviePlay(String[] movieplay_seq) {
		
		int result = 0;
		
		for (String seq : movieplay_seq) {
			
			result += dao.delMoviePlay(seq);
		}
		
		return result;
	}

}
