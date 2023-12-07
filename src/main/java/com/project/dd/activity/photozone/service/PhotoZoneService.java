package com.project.dd.activity.photozone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.activity.photozone.domain.PhotoZoneDTO;
import com.project.dd.activity.photozone.domain.PhotoZoneImgDTO;
import com.project.dd.activity.photozone.repository.PhotoZoneDAO;

@Service
public class PhotoZoneService {

	@Autowired
	PhotoZoneDAO dao;

	public List<PhotoZoneDTO> getPhotozoneList() {
		return dao.getPhotozoneList();
	}

	public PhotoZoneDTO getPhotozone(String seq) {
		return dao.getPhotozone(seq);
	}

	public List<PhotoZoneImgDTO> getPhotozoneImgList(String seq) {
		return dao.getPhotozoneImgList(seq);
	}
}
