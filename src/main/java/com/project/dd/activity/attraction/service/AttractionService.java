package com.project.dd.activity.attraction.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.attraction.domain.AttractionImgDTO;
import com.project.dd.activity.attraction.repository.AttractionDAO;

@Service
public class AttractionService {

	@Autowired
	AttractionDAO dao;

	public List<AttractionDTO> getAttractionList(Map<String, String> map) {
		return dao.getAttractionList(map);
	}

	public AttractionDTO getAttraction(String seq) {
		return dao.getAttraction(seq);
	}

	public List<AttractionImgDTO> getAttractionImgList(String seq) {
		return dao.getAttractionImgList(seq);
	}

	public int getAttractionCloseCount(List<AttractionDTO> list) {

		int closeCount = 0;

		//금일 운휴 어트랙션 개수 세기
		for (AttractionDTO dto : list) {

			if (dto.getClose().equalsIgnoreCase("y")) { //운휴
				closeCount++;
			}
		}
		
		return closeCount;
	}

	public Map<String, String> paging(int page, String solting) {
		
		int pageSize = 0;
		
		//user or admin 노출 목록 개수 설정
		if (solting.equalsIgnoreCase("user")) {
			pageSize = 9;  //나타났으면 하는 개수(user)
			
		} else if (solting.equalsIgnoreCase("admin")) {
			pageSize = 10;  //나타났으면 하는 개수(admin)
		}
	      
		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;

		Map<String, String> map = new HashMap<String, String>();

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));

		int totalPosts = dao.getTotalCount();
		int totalPages = (int) Math.ceil((double) totalPosts / pageSize);

		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));

		return map;

	}

	public int checkLocationDuplication(AttractionDTO dto) {
		return dao.checkLocationDuplication(dto);
	}

	public int addAttraction(AttractionDTO dto, MultipartFile[] imgs, HttpServletRequest req) {
		
		dto.setImgList(new ArrayList<AttractionImgDTO>()); //첨부 파일 배열
		
		for (MultipartFile file : imgs) {
			
			try {
			
				UUID uuid = UUID.randomUUID();
			
				String filename = uuid + "_" + file.getOriginalFilename();
			
				file.transferTo(new File(req.getRealPath("/resources/files/activity/attraction") + "\\" + filename));
				
				//첨부파일 1개 > PicDTO 1개
				AttractionImgDTO idto = new AttractionImgDTO();
				idto.setImg(filename);
				
				dto.getImgList().add(idto);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		//테스트용 실제 경로 출력
//		System.out.println(req.getRealPath("/resources/files/activity/attraction"));
		
		return dao.addAttraction(dto);
	}
	
	
	
}
