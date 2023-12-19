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

@Service
public class LostPropertyService {
	
	@Autowired
	private LostPropertyDAO dao;
	
	/* 페이징 */

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
	
	/* 목록 */

	public List<LostPropertyDTO> getLostPropertyList(Map<String, String> map) {
		
		List<LostPropertyDTO> list = dao.getLostPropertyList(map);
		
		return list;
		
	}
	
	/* 파일 저장 */
	
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
	
	/* 파일 추가 */
	
	public LostPropertyDTO addFile(LostPropertyDTO dto, HttpServletRequest req, MultipartFile doc) {
		
		if (doc == null || doc.isEmpty()) {
			
			dto.setImg(null);
			
		} else {
			
			dto.setImg(saveFile(req, doc));
			
		}

		return dto;
		
	}
	
	/* 추가 */

	public int addLostProperty(LostPropertyDTO dto) {
		
		return dao.addLostProperty(dto);
		
	}
	
	/* 상세 */

	public LostPropertyDTO getLostProperty(String seq) {
		
		LostPropertyDTO dto = dao.getLostProperty(seq);
		
		return dto;

	}
	
	/* 파일 수정 */
	
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
	
	/* 수정 */

	public int editLostProperty(LostPropertyDTO dto) {

		return dao.editLostProperty(dto);
		
	}
	
	/* 삭제 */

	public void deleteLostProperty(String[] seqList) {

		for (String seq : seqList) {
			
			dao.deleteLostProperty(seq);
			
		}
		
	}

}
