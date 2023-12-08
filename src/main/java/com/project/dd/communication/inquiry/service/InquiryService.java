package com.project.dd.communication.inquiry.service;

import java.io.File;
import java.io.IOException;

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

	public int addInquiry(InquiryDTO dto) {
		
		return dao.addInquiry(dto);
		
	}

	public void saveFile(HttpServletRequest req, MultipartFile attach) {
			
        try {
            
        	String path = req.getRealPath("/resources/files/communication/inquiry");

			attach.transferTo(new File(path));

        } catch (IOException e) {
        	
            e.printStackTrace();
            
        }
		
	}

}
