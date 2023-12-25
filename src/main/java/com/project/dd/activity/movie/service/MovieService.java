package com.project.dd.activity.movie.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.activity.movie.domain.MovieDTO;
import com.project.dd.activity.movie.repository.MovieDAO;

/**
 * 
 * 관리자 영화 관리 페이지의 비즈니스 로직을 담당하는 Service 클래스입니다.
 * 
 * @author 박나래
 *
 */
@Service
public class MovieService {

	@Autowired
	MovieDAO dao;

	/**
	 * 
	 * 영화 관리자 페이지의 페이지 번호를 출력하기 위해 페이지당 노출 목록 개수 설정 및 검색 결과값의 개수를 조회하는 메서드입니다. 
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
	 * 전체 영화 목록을 가져오는 메서드입니다.
	 * 
	 * @param map 페이징을 위한 map 객체
	 * @return MovieDTO 객체 List
	 */
	public List<MovieDTO> getMovieList(Map<String, String> map) {
		return dao.getMovieList(map);
	}

	/**
	 * 
	 * 영화를 추가하는 메서드입니다.
	 * 
	 * @param dto MovieDTO 객체
	 * @param imgs MultipartFile 객체(추가한 이미지)
	 * @param req HttpServletRequest 객체
	 * @return 추가된 행의 개수
	 */
	public int addMovie(MovieDTO dto, MultipartFile imgs, HttpServletRequest req) {
		
		
		if (imgs.isEmpty()) {
			
			dto.setImg("movie.png");
			
		} else {
			
			try {
				
				UUID uuid = UUID.randomUUID();
				
				String filename = uuid + "_" + imgs.getOriginalFilename();
				
				imgs.transferTo(new File(req.getRealPath("/resources/files/activity/movie") + "\\" + filename));
				
				dto.setImg(filename);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return dao.addMovie(dto);
	}

	/**
	 * 
	 * 특정 영화 정보를 가져오는 메서드입니다.
	 * 
	 * @param seq 영화 번호
	 * @return MovieDTO 객체
	 */
	public MovieDTO getMovie(String seq) {
		return dao.getMovie(seq);
	}

	/**
	 * 
	 * 영화를 수정하는 메서드입니다.
	 * 
	 * @param dto MovieDTO 객체
	 * @param imgs MultipartFile 객체(수정한 이미지)
	 * @param req HttpServletRequest 객체
	 * @return 수정된 행의 개수
	 */
	public int editMovie(MovieDTO dto, MultipartFile imgs, HttpServletRequest req) {
		
		//imgs == null > input:disabled > 기존 첨부파일 그대로인 상태 > img 수정 없음
		if (imgs == null) {
			
			//기존 img 파일명 가져오기
			String imgFileName = dao.getMovieImgFileName(dto.getMovie_seq());
			
			dto.setImg(imgFileName);
			
		} else if (imgs.isEmpty()) { //기존 img 삭제 > default(movie.png) 사용
			
			dto.setImg("movie.png");
			
		} else { //이미지 변경하여 첨부
			
			try {
				
				UUID uuid = UUID.randomUUID();
				
				String filename = uuid + "_" + imgs.getOriginalFilename();
				
				imgs.transferTo(new File(req.getRealPath("/resources/files/activity/movie") + "\\" + filename));
				
				dto.setImg(filename);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return dao.editMovie(dto);
	}

	/**
	 * 
	 * 영화를 삭제하는 메서드입니다.
	 * 
	 * @param movie_seq 영화 번호
	 * @return 삭제된 행의 개수
	 */
	//영화 삭제
	// - 1. 배열 돌면서 seq 뽑아내기
	// - 2. 해당하는 seq의 레코드 UPDATE (상영종료)
	public int delMovie(String[] movie_seq) {
		
		int result = 0;
		
		//1. 배열 돌면서 seq 뽑아내기
		for (String seq : movie_seq) {
			
			//2. 해당하는 seq의 레코드 UPDATE (상영종료)
			result += dao.delMovie(seq);
		}
		
		return result;
	}
}
