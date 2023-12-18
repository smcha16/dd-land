package com.project.dd.communication.inquiry.service;

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

import com.project.dd.communication.inquiry.domain.InquiryDTO;
import com.project.dd.communication.inquiry.repository.InquiryDAO;

@Service
public class InquiryService {
	
	@Autowired
	private InquiryDAO dao;
	
	/* 파일 저장 */

	public String saveFile(HttpServletRequest req, MultipartFile doc) {
			
        try {
        	
        	String path = req.getRealPath("/resources/files/communication/inquiry");
        	
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
	
	public InquiryDTO addFile(InquiryDTO dto, HttpServletRequest req, MultipartFile doc) {
		
		if (doc == null || doc.isEmpty()) {
			
			dto.setAttach(null);
			
		} else {
			
			dto.setAttach(saveFile(req, doc));
			
		}

		return dto;
		
	}
	
	/* 추가 */
	
	public int addInquiry(InquiryDTO dto) {
		
		return dao.addInquiry(dto);
		
	}
	
	/* 페이징 */

	public Map<String, String> paging(int page) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		int pageSize = 10;
		
		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));
		
		int totalPosts = dao.getTotalCount();
		int totalPages = (int)Math.ceil((double)totalPosts / pageSize);
		
		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));
		
		return map;
		
	}
	
	/* 목록 */

	public List<InquiryDTO> getInquiryList(Map<String, String> map) {
		
		List<InquiryDTO> list = dao.getInquiryList(map);
		
		return list;
		
	}
	
	/* 답변 */

	public void editAnswer(InquiryDTO dto) {
		
		dao.editAnswer(dto);
		
	}
	
}
