package com.project.dd.communication.lost.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.communication.lost.domain.LostPropertyDTO;
import com.project.dd.communication.lost.repository.LostPropertyDAO;

/**
 * 분실물 서비스 클래스입니다.
 * 
 * @author sumin
 */
@Service
public class LostPropertyService {
	
	@Autowired
	private LostPropertyDAO dao;

	/**
	 * 페이징 처리를 위한 맵을 생성하는 메서드입니다.
	 *
	 * @param searchStatus 검색 상태
	 * @param category 분실물 카테고리
	 * @param word 검색어
	 * @param start 검색 시작일
	 * @param end 검색 종료일
	 * @param page 페이지 번호
	 * @return 페이징 정보를 담은 Map 객체
	 */
	public Map<String, String> paging(String searchStatus, String category, String word, String start, String end, int page) {
		
		Map<String, String> map = new HashMap<String, String>();

		map.put("searchStatus", searchStatus);
		map.put("category", category);
		map.put("word", word);
		map.put("start", start);
		map.put("end", end);

		int pageSize = 10;
		
		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));
		
		int totalPosts = dao.getTotalCount(map);
		int totalPages = (int)Math.ceil((double)totalPosts / pageSize);
		
		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));
		
		return map;
		
	}

	/**
	 * 분실물 목록을 가져오는 메서드입니다.
	 *
	 * @param map 페이징 정보를 포함한 Map 객체
	 * @return 분실물 목록
	 */
	public List<LostPropertyDTO> getLostPropertyList(Map<String, String> map) {
		
		List<LostPropertyDTO> list = dao.getLostPropertyList(map);
		
		return list;
		
	}
	
	/**
	 * 파일을 저장하는 메서드입니다.
	 *
	 * @param req HttpServletRequest 객체
	 * @param doc 첨부 파일
	 * @return 저장된 파일명
	 */
	public String saveFile(HttpServletRequest req, MultipartFile doc) {
		
		try {
        	
        	String path = req.getRealPath("/resources/files/communication/lost");
        	
        	File directory = new File(path);
        	
            if (!directory.exists()) {
            	
                directory.mkdirs();
                
            }

            UUID uuid = UUID.randomUUID();
            
            String fileName = uuid + "_" + doc.getOriginalFilename();

            Path filePath = Paths.get(path, fileName);

            doc.transferTo(filePath.toFile());
            
            return fileName;

        } catch (IOException e) {
        	
            e.printStackTrace();
            
        }
		
		return null;
		
	}
	
	/**
	 * 파일을 추가하는 메서드입니다.
	 *
	 * @param dto 추가할 분실물의 DTO 객체
	 * @param req HttpServletRequest 객체
	 * @param doc 첨부 파일
	 * @return 파일이 추가된 DTO 객체
	 */	
	public LostPropertyDTO addFile(LostPropertyDTO dto, HttpServletRequest req, MultipartFile doc) {
		
		if (doc == null || doc.isEmpty()) {
			
			dto.setImg(null);
			
		} else {
			
			dto.setImg(saveFile(req, doc));
			
		}

		return dto;
		
	}

	/**
	 * 분실물을 추가하는 메서드입니다.
	 *
	 * @param dto 추가할 분실물의 DTO 객체
	 * @return 추가 결과 (1: 성공, 0: 실패)
	 */
	public int addLostProperty(LostPropertyDTO dto) {
		
		return dao.addLostProperty(dto);
		
	}

	/**
	 * 특정 분실물의 상세 내용을 가져오는 메서드입니다.
	 *
	 * @param seq 조회할 분실물의 일련번호
	 * @return 조회된 분실물의 DTO 객체
	 */
	public LostPropertyDTO getLostProperty(String seq) {
		
		LostPropertyDTO dto = dao.getLostProperty(seq);
		
		return dto;

	}
	
	/**
	 * 파일을 수정하는 메서드입니다.
	 *
	 * @param dto 수정할 분실물의 DTO 객체
	 * @param req HttpServletRequest 객체
	 * @param doc 첨부 파일
	 * @return 파일이 수정된 DTO 객체
	 */
	public LostPropertyDTO editFile(LostPropertyDTO dto, HttpServletRequest req, MultipartFile doc) {

		if (doc == null) {
			
			dto.setImg(dao.getFileName(dto.getLost_property_seq()));
			
		} else if (doc.isEmpty()) {
			
			dto.setImg(null);
			
		} else {
			
			dto.setImg(saveFile(req, doc));
			
		}

		return dto;
		
	}

	/**
	 * 분실물을 수정하는 메서드입니다.
	 *
	 * @param dto 수정할 분실물의 DTO 객체
	 * @return 수정 결과 (1: 성공, 0: 실패)
	 */
	public int editLostProperty(LostPropertyDTO dto) {

		return dao.editLostProperty(dto);
		
	}

	/**
	 * 여러 개의 분실물을 삭제하는 메서드입니다.
	 *
	 * @param seqList 삭제할 분실물의 일련번호 배열
	 */
	public void deleteLostProperty(String[] seqList) {

		for (String seq : seqList) {
			
			dao.deleteLostProperty(seq);
			
		}
		
	}

}
