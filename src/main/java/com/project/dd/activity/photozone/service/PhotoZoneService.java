package com.project.dd.activity.photozone.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.activity.photozone.domain.PhotoZoneDTO;
import com.project.dd.activity.photozone.domain.PhotoZoneImgDTO;
import com.project.dd.activity.photozone.repository.PhotoZoneDAO;

@Service
public class PhotoZoneService {

	@Autowired
	PhotoZoneDAO dao;

	public List<PhotoZoneDTO> getPhotozoneList(Map<String, String> map) {
		return dao.getPhotozoneList(map);
	}

	public PhotoZoneDTO getPhotozone(String seq) {
		return dao.getPhotozone(seq);
	}

	public List<PhotoZoneImgDTO> getPhotozoneImgList(String seq) {
		return dao.getPhotozoneImgList(seq);
	}

	public Map<String, String> paging(int page) {
		 int pageSize = 9;  //나타났으면 하는 개수
	      
	      int startIndex = (page - 1) * pageSize + 1;
	      int endIndex = startIndex + pageSize - 1;
	      
	      Map<String, String> map = new HashMap<String, String>();

	      map.put("startIndex", String.format("%d", startIndex));
	      map.put("endIndex", String.format("%d", endIndex));
	      
	      int totalPosts = dao.getTotalCount();
	      int totalPages = (int)Math.ceil((double)totalPosts / pageSize);
	      
	      map.put("totalPosts", String.format("%d", totalPosts));
	      map.put("totalPages", String.format("%d", totalPages));
	      
	      return map;

	}
}
