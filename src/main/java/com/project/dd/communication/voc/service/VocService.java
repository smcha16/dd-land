package com.project.dd.communication.voc.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.communication.voc.domain.VocDTO;
import com.project.dd.communication.voc.repository.VocDAO;

/**
 * 칭찬/불편/건의 서비스 클래스입니다.
 * 
 * @author sumin
 */
@Service
public class VocService {
	
	@Autowired
	private VocDAO dao;
	
	/**
	 * 방문일 목록을 가져오는 메서드입니다.
	 *
	 * @param email 로그인한 사용자의 이메일
	 * @return 방문일 목록
	 */
	public List<String> getVisitDateList(String email) {
		
		List<String> visitDateList = dao.getVisitDateList(email);
		
		List<String> list = new ArrayList<String>();

		for (String date : visitDateList) {
			
		    list.add(date.substring(0, 10));

		}

		
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
        	
        	String path = req.getRealPath("/resources/files/communication/voc");
        	
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
	 * @param dto 추가할 칭찬/불편/건의의 DTO 객체
	 * @param req HttpServletRequest 객체
	 * @param doc 첨부 파일
	 * @return 파일이 추가된 DTO 객체
	 */
	public VocDTO addFile(VocDTO dto, HttpServletRequest req, MultipartFile doc) {
		
		if (doc == null || doc.isEmpty()) {
			
			dto.setAttach(null);
			
		} else {
			
			dto.setAttach(saveFile(req, doc));
			
		}

		return dto;
		
	}

	/**
	 * 칭찬/불편/건의를 추가하는 메서드입니다.
	 *
	 * @param dto 추가할 칭찬/불편/건의의 DTO 객체
	 * @return 추가 결과 (1: 성공, 0: 실패)
	 */
	public int addVoc(VocDTO dto) {

		return dao.addVoc(dto);
		
	}
	
	/**
	 * 페이징 처리를 위한 맵을 생성하는 메서드입니다.
	 *
	 * @param searchStatus 검색 상태
	 * @param word 검색어
	 * @param page 페이지 번호
	 * @return 페이징 정보를 담은 Map 객체
	 */
	public Map<String, String> paging(String searchStatus, String word, int page) {
		
		Map<String, String> map = new HashMap<String, String>();

		map.put("searchStatus", searchStatus);
		map.put("word", word);
		
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
	 * 칭찬/불편/건의 목록을 가져오는 메서드입니다.
	 *
	 * @param map 페이징 정보가 담긴 Map 객체
	 * @return 칭찬/불편/건의 목록
	 */
	public List<VocDTO> getVocList(Map<String, String> map) {
		
		List<VocDTO> list = dao.getVocList(map);
		
		return list;
		
	}
	
	/**
	 * 칭찬/불편/건의에 답변을 등록하는 메서드입니다.
	 *
	 * @param dto 답변할 칭찬/불편/건의의 DTO 객체
	 */
	public void editAnswer(VocDTO dto) {
		
		dao.editAnswer(dto);
		
	}

}
