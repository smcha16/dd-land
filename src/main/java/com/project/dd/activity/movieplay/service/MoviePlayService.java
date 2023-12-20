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
 * 영화 상영 DB에 접근하여 실행된 레코드의 수를 반환하는 Service 클래스입니다.
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
	 * 페이지 번호를 출력하기 위해 DB에 접근하여 영화 상영 개수를 조회하는 메서드입니다.
	 * 
	 * @param page 페이지 번호
	 * @param solting 사용자/관리자별 한 페이지당 노출 목록 개수 설정
	 * @return 위의 정보가 담긴 map 객체
	 */
	public Map<String, String> paging(int page, String solting) {
		
		int pageSize = 0;
		
		//user or admin 노출 목록 개수 설정
		if (solting.equalsIgnoreCase("user")) {
			pageSize = 9;  //나타났으면 하는 개수(user)
			
		} else if (solting.equalsIgnoreCase("admin")) {
			pageSize = 10;  //나타났으면 하는 개수(admin)
		}
	      
		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;

		Map<String, String> map = new HashMap<String, String>();

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));

		int totalPosts = dao.getTotalCount(solting);
		int totalPages = (int) Math.ceil((double) totalPosts / pageSize);

		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));

		return map;
	}

	/**
	 * 
	 * 전체 영화 상영 목록을 불러오는 메서드입니다.
	 * 
	 * @param map 페이징을 위한 map 객체
	 * @return 영화상영 dto list
	 */
	public List<MoviePlayDTO> getMoviePlayListAll(Map<String, String> map) {
		return dao.getMoviePlayListAll(map);
	}

	/**
	 * 
	 * 영화 상영 추가를 위해 영화 상영 DB에 접근하느 메서드입니다.
	 * 
	 * @param dto 영화 상영 dto 객체
	 * @return 테이블에 추가된 행의 개수
	 */
	public int addMoviePlay(MoviePlayDTO dto) {
		return dao.addMoviePlay(dto);
	}

	/**
	 * 
	 * 영화 상영을 삭제하기 위해 DB에 접근하여 삭제하는 메서드입니다.
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

	/**
	 * 
	 * 영화 상영 수정을 위해 영화 상영 테이블에 접근하는 메서드입니다.
	 * 
	 * @param dto 영화 상영 dto 객체
	 * @return 수정된 행의 개수
	 */
	public int editMoviePlay(MoviePlayDTO dto) {
		return dao.editMoviePlay(dto);
	}

	/**
	 * 
	 * 특정 영화 상영 정보를 가져오는 메서드입니다.
	 * 
	 * @param seq 영화 상영 번호
	 * @return 영화상영 dto
	 */
	public MoviePlayDTO getMoviePlay(String seq) {
		return dao.getMoviePlay(seq);
	}

	/**
	 * 
	 * 영화 목록을 가져오는 메서드입니다.
	 * 
	 * @return 영화 dto list
	 */
	public List<MovieDTO> getMovieList() {
		return dao.getMovieList();
	}

	/**
	 * 
	 * 영화관 목록을 가져오는 메서드입니다.
	 * 
	 * @return 영화관 dto list
	 */
	public List<TheaterDTO> getTheaterList() {
		return dao.getTheaterList();
	}

}
