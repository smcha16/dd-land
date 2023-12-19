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

@Service
public class NoticeService {
	
	@Autowired
	private NoticeDAO dao;
	
	/* 페이징 */

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
	
	/* 목록 */

	public List<NoticeDTO> getNoticeList(Map<String, String> map) {
		
		List<NoticeDTO> list = dao.getNoticeList(map);
		
		return list;
		
	}
	
	/* 상세 */

	public NoticeDTO getNotice(String seq) {
		
		NoticeDTO dto = dao.getNotice(seq);
		
		return dto;
		
	}
	
	/* 파일 저장 */
	
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
	
	/* 파일 추가 */
	
	public NoticeDTO addFile(NoticeDTO dto, HttpServletRequest req, MultipartFile doc) {
		
		if (doc == null || doc.isEmpty()) {
			
			dto.setAttach(null);
			
		} else {
			
			dto.setAttach(saveFile(req, doc));
			
		}

		return dto;
		
	}
	
	/* 추가 */

	public int addNotice(NoticeDTO dto) {
		
		return dao.addNotice(dto);
		
	}
	
	/* 파일 수정 */
	
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
	
	/* 수정 */

	public int editNotice(NoticeDTO dto) {
		
		return dao.editNotice(dto);
		
	}
	
	/* 삭제 */

	public void deleteNotice(String[] seqList) {

		for (String seq : seqList) {
			
			dao.deleteNotice(seq);
			
		}

	}

}
