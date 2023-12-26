package com.project.dd.activity.festival.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.activity.attraction.domain.AttractionImgDTO;
import com.project.dd.activity.festival.domain.FestivalDTO;
import com.project.dd.activity.festival.domain.FestivalImgDTO;
import com.project.dd.activity.festival.repository.FestivalDAO;

/**
 * 
 * 페스티벌 DB에 접근하여 실행된 레코드의 수를 반환하는 Service 클래스입니다.
 * 
 * @author 박나래
 *
 */
@Service
public class FestivalService {

	@Autowired
	FestivalDAO dao;

	/**
	 * 
	 * 선택한 날짜에 공연중인 페스티벌의 목록을 조회하는 메서드입니다.
	 * 
	 * @param date 날짜
	 * @return 페스티벌 dto 객체 list
	 */
	public List<FestivalDTO> getFestivalList(String date) {
		List<FestivalDTO> list = dao.getFestivalList(date);
		
		for (FestivalDTO dto : list) {
			
			//DB 내 태그 비활성화 처리 '&gt;, &lt;' 처리
			String newInfo = dto.getInfo(); 
			newInfo = newInfo.replace("<", "&lt");
			newInfo = newInfo.replace(">", "&gt");
			
			//DB 개행 -> '<br>' 태그 처리
			newInfo = newInfo.replaceAll("(\r\n|\r|\n)", "<br>");
			
			dto.setInfo(newInfo);
			
		}
		
		return list;
		
	}

	/**
	 * 
	 * 특정 번호의 페스티벌 정보를 가져오는 메서드
	 * 
	 * @param seq 페스티벌 번호
	 * @return 페스티벌 dto 객체
	 */
	public FestivalDTO getFestival(String seq) {
		
		FestivalDTO dto = dao.getFestival(seq);
		
		//DB 내 태그 비활성화 처리 '&gt;, &lt;' 처리
		String newInfo = dto.getInfo(); 
		newInfo = newInfo.replace("<", "&lt");
		newInfo = newInfo.replace(">", "&gt");
		
		//DB 개행 -> '<br>' 태그 처리
		newInfo = newInfo.replaceAll("(\r\n|\r|\n)", "<br>");
		
		dto.setInfo(newInfo);
		
		return dto;
	}

	/**
	 * 
	 * 전체 페스티벌 이미지를 가져오는 메서드
	 * 
	 * @param seq 페스티벌 번호
	 * @return 페스티벌 이미지 dto 객체가 담긴 list
	 */
	public List<FestivalImgDTO> getFestivalImgList(String seq) {
		return dao.getFestivalImgList(seq);
	}

	/**
	 * 
	 * 전체 페스티벌 목록을 가져오는 메서드입니다.
	 * 
	 * @param map 페이징을 위한 Map 객체
	 * @return FestivalDTO 객체 List
	 */
	public List<FestivalDTO> getAllFestivalList(Map<String, String> map) {
		
		List<FestivalDTO> list = dao.getAllFestivalList(map);
		
		for (FestivalDTO dto : list) {
			
			//DB 내 태그 비활성화 처리 '&gt;, &lt;' 처리
			String newInfo = dto.getInfo(); 
			newInfo = newInfo.replace("<", "&lt");
			newInfo = newInfo.replace(">", "&gt");
			
			//DB 개행 -> '<br>' 태그 처리
			newInfo = newInfo.replaceAll("(\r\n|\r|\n)", "<br>");
			
			dto.setInfo(newInfo);
			
		}
		
		return list;
	}

	/**
	 * 
	 * 페이지 번호를 출력하기 위해 DB에 접근하여 페스티벌 개수를 조회하는 메서드입니다.
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
	 * 전체 페스티벌 이미지 목록을 가져오는 메서드
	 * 
	 * @return 페스티벌 이미지 dto 객체 list
	 */
	public List<FestivalImgDTO> getAllFestivalImgList() {
		return dao.getAllFestivalImgList();
	}

	/**
	 * 
	 * 페스티벌 추가를 위해 페스티벌, 페스티벌 위치, 페스티벌 이미지 DB에 접근하는 메서드
	 * 
	 * @param dto 페스티벌 dto 객체
	 * @param imgs 첨부할 이미지 멀티파일 객체 배열
	 * @param req HttpServletRequest 객체
	 * @return 테이블에 추가된 행의 개수
	 */
	public int addFestival(FestivalDTO dto, MultipartFile[] imgs, HttpServletRequest req) {

		//Add Festival
		// - 1. tblFestival INSERT
		// - 2. tblFestivalLocation INSERT
		// - 3. tblFestivalImg INSERT
		
		// - 1. tblFestival INSERT
		int result = dao.addFestival(dto);
		
		//get Festival_seq
		String seq = dao.getFestivalSeq();
		dto.setFestival_seq(seq);
		
		// - 2. tblFestivalLocation INSERT
		result = dao.addFestivalLocation(dto);
		
		// - 3. tblFestivalImg INSERT
		dto.setImgList(new ArrayList<FestivalImgDTO>()); //첨부 파일 배열
		
		if (imgs[0].isEmpty()) {
			
			FestivalImgDTO idto = new FestivalImgDTO();
			
			idto.setImg("festival.png");
			
			dto.getImgList().add(idto);
			
		} else {
			
			for (MultipartFile file : imgs) {
				
				try {
					
					UUID uuid = UUID.randomUUID();
					
					String filename = uuid + "_" + file.getOriginalFilename();
					
					file.transferTo(new File(req.getRealPath("/resources/files/activity/festival") + "\\" + filename));
					
					//첨부파일 1개 > PicDTO 1개
					FestivalImgDTO idto = new FestivalImgDTO();
					idto.setImg(filename);
					
					dto.getImgList().add(idto);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		}//if
		
		for (FestivalImgDTO idto : dto.getImgList()) {
			
			idto.setFestival_seq(seq);
			
			result += dao.addFestivalImg(idto);
		}
		
		return result;
	}

	/**
	 * 
	 * 페스티벌 수정을 위해 페스티벌, 페스티벌 위치, 페스티벌 이미지에 접근하는 메서드
	 * 
	 * @param dto 페스티벌 dto 객체
	 * @param imgs 추가한 멀티파일 객체 배열
	 * @param req HttpServletRequest 객체
	 * @param deleteImgSeq 삭제할 페스티벌 번호
	 * @return 수정된 행의 개수
	 */
	public int editFestival(FestivalDTO dto, MultipartFile[] imgs, HttpServletRequest req, String[] deleteImgSeq) {

		//Editing Festival's 7 Cases
		String seq = dto.getFestival_seq();
		int result = 0;
		
		//mandatory procedure
		// - tblFestival UPDATE
		// - tblFestivalLocation UPDATE
		result = dao.editFestival(dto);
		result = dao.editFestivalLocation(dto);
		
		//optional procedure?! not only optional..though..
		if (dto.getImg() != null && dto.getImg().equalsIgnoreCase("festival.png")) { //not exist attached file > Case 1, Case 2
			
			if (!imgs[0].isEmpty()) {
				
				dao.delFestivalImg(seq);
				
				dto.setImgList(new ArrayList<FestivalImgDTO>());
				
				for (MultipartFile file : imgs) {
					
					try {
						
						UUID uuid = UUID.randomUUID();
						
						String filename = uuid + "_" + file.getOriginalFilename();
						
						file.transferTo(new File(req.getRealPath("/resources/files/activity/festival") + "\\" + filename));
						
						//1 attached file > 1 FestivalImgDTO
						FestivalImgDTO idto = new FestivalImgDTO();
						idto.setImg(filename);
						
						dto.getImgList().add(idto);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}//for
				
				for (FestivalImgDTO idto : dto.getImgList()) {
					
					idto.setFestival_seq(seq);
					
					result += dao.addFestivalImg(idto); //Case 2
				}
				
				return result;
			}
			
			return 1; //Case 1
			
		} 
		
		// - exist attached files > Case 3 ~ Case 7
		if (dto.getImg() != null && imgs[0].isEmpty()) { //Case 3, Case 4, Case 5
			
			int imgCount = dao.countFestivalImg(seq);
			
			if (deleteImgSeq.length > 0 && deleteImgSeq.length < imgCount) { //partially delete
				
				for (String imgseq : deleteImgSeq) {
					result = dao.delFestivalImgByImgSeq(imgseq); //Case 4
				}
				
			} else if (deleteImgSeq.length == imgCount) { //all delete
				
				for (String imgseq : deleteImgSeq) {
					result = dao.delFestivalImgByImgSeq(imgseq); //Case 5
				}
				
				//'festival.png' 추가
				FestivalImgDTO idto = new FestivalImgDTO();
				idto.setFestival_seq(seq);
				idto.setImg("festival.png");
				
				dao.addFestivalImg(idto);
				
			} else if (deleteImgSeq.length == 0) {
				return 1; //Case 3
			}
			
		} else if (dto.getImg() != null && !imgs[0].isEmpty()) { //Case 6, Case 7
			
			//optional procedure > delete existed files
			if (deleteImgSeq.length > 0) {
				for (String imgseq : deleteImgSeq) {
					result = dao.delFestivalImgByImgSeq(imgseq);
				}
			}
			
			//mandatory procedure > add new attached files
			dto.setImgList(new ArrayList<FestivalImgDTO>());
			
			for (MultipartFile file : imgs) {
				
				try {
					
					UUID uuid = UUID.randomUUID();
					
					String filename = uuid + "_" + file.getOriginalFilename();
					
					file.transferTo(new File(req.getRealPath("/resources/files/activity/festival") + "\\" + filename));
					
					//1 attached file > 1 FestivalImgDTO
					FestivalImgDTO idto = new FestivalImgDTO();
					idto.setImg(filename);
					
					dto.getImgList().add(idto);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			for (FestivalImgDTO idto : dto.getImgList()) {
				
				idto.setFestival_seq(seq);
				
				result += dao.addFestivalImg(idto);
			}
			
		}
		
		return result;
	}

	/**
	 * 
	 * 페스티벌을 삭제하게 위해 DB에 접근하여 페스티벌 위치, 페스티벌 이미지, 페스티벌을 삭제하는 메서드
	 * 
	 * @param festival_seq 페스티벌 번호
	 * @return 삭제된 행의 개수
	 */
	public int delFestival(String[] festival_seq) {
		
		//Delete Festival
		// - 1. tblFestivalImg DELETE
		// - 2. tblFestivalLocation DELETE
		// - 3. tblFestival DELETE
		
		int result = 0;
		
		for (String seq : festival_seq) {
			
			// - 1. tblFestivalImg DELETE
			int imgCount = dao.countFestivalImg(seq);
			
			if (imgCount > 0) {
				dao.delFestivalImg(seq);
			}
			
			// - 2. tblFestivalLocation DELETE
			int locationCount = dao.countFestivalLocation(seq);
			
			if (locationCount > 0) {
				dao.delFestivalLocation(seq);
			}
			
			// - 3. tblFestival DELETE
			result += dao.delFestival(seq);
		}
		
		return result;
	}

	/**
	 * 
	 * 페스티벌 위치정보의 중복 검사를 진행하는 메서드
	 * 
	 * @param dto 페스티벌 dto 객체
	 * @return 중복된 레코드의 개수
	 */
	public int checkLocationDuplication(FestivalDTO dto) {
		return dao.checkLocationDuplication(dto);
	}

	/**
	 * 
	 * 페이지 번호를 출력하기 위해 페이지당 노출 목록 개수 설정 및 검색 결과값의 개수를 조회하는 메서드입니다.
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

}
