package com.project.dd.communication.voc.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.communication.voc.domain.VocDTO;
import com.project.dd.communication.voc.repository.VocDAO;

@Service
public class VocService {
	
	@Autowired
	private VocDAO dao;
	
	public List<String> getVisitDateList(String email) {
		
		List<String> visitDateList = dao.getVisitDateList(email);
		
		List<String> list = new ArrayList<String>();

		for (String date : visitDateList) {
			
		    list.add(date.substring(0, 10));

		}

		
		return list;
		
	}

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

	public int addVoc(VocDTO dto) {

		return dao.addVoc(dto);
		
	}

}
