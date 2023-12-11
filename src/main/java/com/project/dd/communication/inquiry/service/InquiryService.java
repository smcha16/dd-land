package com.project.dd.communication.inquiry.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
	
	public int addInquiry(InquiryDTO dto) {
		
		return dao.addInquiry(dto);
		
	}

}
