package com.project.dd.communication.notice.service;

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

import com.project.dd.communication.notice.domain.NoticeDTO;
import com.project.dd.communication.notice.repository.NoticeDAO;

/**
 * 공지사항 서비스 클래스입니다.
 * 
 * @author sumin
 */
@Service
public class NoticeService {
	
	@Autowired
	private NoticeDAO dao;

	/**
	 * 페이징 처리를 위한 맵을 생성하는 메서드입니다.
	 *
	 * @param solting 정렬 기준
	 * @param searchStatus 검색 상태
	 * @param category 카테고리
	 * @param word 검색어
	 * @param page 페이지 번호
	 * @return 페이징 정보를 담은 Map 객체
	 */
	public Map<String, String> paging(String solting, String searchStatus, String category, String word, int page) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("solting", solting);
		map.put("searchStatus", searchStatus);
		map.put("category", category);
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
	 * 공지사항 목록을 가져오는 메서드입니다.
	 *
	 * @param map 페이징 정보가 담긴 Map 객체
	 * @return 공지사항 목록
	 */
	public List<NoticeDTO> getNoticeList(Map<String, String> map) {
		
		List<NoticeDTO> list = dao.getNoticeList(map);
		
		for (NoticeDTO dto : list) {
			
			String content = dto.getContent();
			    
			if (content != null) {
				
				content = content.replace("<", "&lt;");
			    content = content.replace(">", "&gt;");
			    content = content.replace("\\r\\n", "<br>");

			    dto.setContent(content);
			    
			}
			
		}

		return list;
		
	}

	/**
	 * 특정 공지사항의 상세 내용을 가져오는 메서드입니다.
	 *
	 * @param seq 조회할 공지사항의 일련번호
	 * @return 조회된 공지사항의 DTO 객체
	 */
	public NoticeDTO getNotice(String seq) {
		
		NoticeDTO dto = dao.getNotice(seq);
		
		String content = dto.getContent();
		    
		if (content != null) {

			content = content.replace("<", "&lt;");
		    content = content.replace(">", "&gt;");
		    content = content.replace("\\r\\n", "<br>");

		    dto.setContent(content);
		    
		}
		
		return dto;
		
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
        	
        	String path = req.getRealPath("/resources/files/communication/notice");
        	
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
	 * @param dto 추가할 공지사항의 DTO 객체
	 * @param req HttpServletRequest 객체
	 * @param doc 첨부 파일
	 * @return 파일이 추가된 DTO 객체
	 */
	public NoticeDTO addFile(NoticeDTO dto, HttpServletRequest req, MultipartFile doc) {
		
		if (doc == null || doc.isEmpty()) {
			
			dto.setAttach(null);
			
		} else {
			
			dto.setAttach(saveFile(req, doc));
			
		}

		return dto;
		
	}

	/**
	 * 공지사항을 추가하는 메서드입니다.
	 *
	 * @param dto 추가할 공지사항의 DTO 객체
	 * @return 추가 결과 (1: 성공, 0: 실패)
	 */
	public int addNotice(NoticeDTO dto) {
		
		return dao.addNotice(dto);
		
	}
	
	/**
	 * 파일을 수정하는 메서드입니다.
	 *
	 * @param dto 수정할 공지사항의 DTO 객체
	 * @param req HttpServletRequest 객체
	 * @param doc 첨부 파일
	 * @return 파일이 수정된 DTO 객체
	 */
	public NoticeDTO editFile(NoticeDTO dto, HttpServletRequest req, MultipartFile doc) {

		if (doc == null) {
			
			dto.setAttach(dao.getFileName(dto.getNotice_seq()));
			
		} else if (doc.isEmpty()) {
			
			dto.setAttach(null);
			
		} else {
			
			dto.setAttach(saveFile(req, doc));
			
		}

		return dto;
		
	}

	/**
	 * 공지사항을 수정하는 메서드입니다.
	 *
	 * @param dto 수정할 공지사항의 DTO 객체
	 * @return 수정 결과 (1: 성공, 0: 실패)
	 */
	public int editNotice(NoticeDTO dto) {
		
		return dao.editNotice(dto);
		
	}

	/**
	 * 여러 개의 공지사항을 삭제하는 메서드입니다.
	 *
	 * @param seqList 삭제할 공지사항의 일련번호 배열
	 */
	public void deleteNotice(String[] seqList) {

		for (String seq : seqList) {
			
			dao.deleteNotice(seq);
			
		}

	}

}
