package com.project.dd.test.mbti.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.test.mbti.domain.MBTIDTO;
import com.project.dd.test.mbti.repository.MBTIDAO;

@Service
@Primary
public class MBTIServiceImpl implements MBTIService {

    @Autowired
    private MBTIDAO dao;

    @Override
    public int getTotalCount() {
    	return dao.getTotalCount();
    }
    
	// 페이징 메서드
	public Map<String, String> paging(int page) {
		int pageSize = 10; // 조회할 글 개수

		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;

		Map<String, String> map = new HashMap<String, String>();

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));

		int totalPosts = getTotalCount();
		int totalPages = (int) Math.ceil((double) totalPosts / pageSize);

		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));

		return map;
	}
	
    @Override
    public List<MBTIDTO> getAllMBTI(Map<String, String> map) {
        return dao.getAllMBTI(map);
    }

    @Override
	public MBTIDTO getMBTI(String seq) {
		return dao.getMBTI(seq);
	}
    
    @Override
	public int addMBTI(MBTIDTO dto, MultipartFile image, HttpServletRequest req) {
		
		if (image.isEmpty()) {
			
			dto.setMbti_img("mbti.png");
			System.out.println(dto.getMbti_img());
			
		} else {
			try {
				
				UUID uuid = UUID.randomUUID();
				
				String filename = uuid + "_" + image.getOriginalFilename();
				
				image.transferTo(new File(req.getRealPath("/resources/files/test/mbti") + "\\" + filename));
				
				dto.setMbti_img(filename);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return dao.addMBTI(dto);
	}
    
    @Override
    public int checkMBTINameDuplication(MBTIDTO dto) {
    	return dao.checkMBTINameDuplication(dto);
    }

}
