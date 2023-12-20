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
import com.project.dd.activity.movieplay.domain.MoviePlayDTO;

/**
 * 
 * 영화 DB에 접근하여 실행된 레코드의 수를 반환하는 Service 클래스입니다.
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
	 * 해당 날짜에 상영하는 영화 목록을 불러오는 메서드입니다.
	 * 
	 * @param date 날짜
	 * @return 영화 dto list
	 */
	public List<MovieDTO> getMovieList(String date) {
		return dao.getMovieList(date);
	}

	/**
	 * 
	 * 특정 번호의 영화 정보를 가져오는 메서드입니다.
	 * 
	 * @param seq 영화 번호
	 * @return 영화 dto 객체
	 */
	public MovieDTO getMovie(String seq) {
		return dao.getMovie(seq);
	}

	/**
	 * 
	 * 특정 번호의 영화 상영 정보 목록을 가져오는 메서드입니다.
	 * 
	 * @param seq 영화 번호
	 * @return 영화상영 dto list
	 */
	public List<MoviePlayDTO> getMoviePlayList(String seq) {
		return dao.getMoviePlayList(seq);
	}

	/**
	 * 
	 * 특정 번호의 영화 상영 정보를 가져오는 메서드입니다.
	 * 
	 * @param seq 영화 번호
	 * @return 영화상영 dto list
	 */
	public List<MoviePlayDTO> getMoviePlay(String seq) {
		return dao.getMoviePlay(seq);
	}

	/**
	 * 
	 * 영화를 삭제하기 위해 DB에 접근하여 영화를 삭제하는 메서드입니다.
	 * 
	 * @param movie_seq 영홯 번호
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

	/**
	 * 
	 * 페이지 번호를 출력하기 위해 DB에 접근하여 영화 개수를 가져오는 메서드입니다.
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
	 * 전체 영화 목록을 가져오는 메서드입니다.
	 * 
	 * @param map 페이징을 위한 map 객체
	 * @return 영화 dto list
	 */
	public List<MovieDTO> getMovieListAll(Map<String, String> map) {
		return dao.getMovieListAll(map);
	}

	/**
	 * 
	 * 영화 추가를 위해 영화 DB에 접근하는 메서드입니다.
	 * 
	 * @param dto 영화 dto 객체
	 * @param imgs 첨부할 이미지 멀티파일 객체
	 * @param req HttpServletRequest 객체
	 * @return 테이블에 추가된 행의 개수
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
	 * 영화 수정을 위해 영화테이블에 접근하는 메서드입니다.
	 * 
	 * @param dto 영화 dto 객체
	 * @param imgs 수정한 멀티파일 객체
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
	 * 영화명의 중복 검사를 위한 메서드
	 * 
	 * @param dto 영화 dto 객체
	 * @return 중복된 레코드의 개수
	 */
	public int checkMovieNameDuplication(MovieDTO dto) {
		return dao.checkMovieNameDuplication(dto);
	}
}
