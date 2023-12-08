package com.project.dd.activity.photozone.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.activity.photozone.domain.PhotoZoneDTO;
import com.project.dd.activity.photozone.domain.PhotoZoneImgDTO;
import com.project.dd.activity.photozone.mapper.PhotoZoneMapper;

@Primary
@Repository
public class PhotoZoneDAOImpl implements PhotoZoneDAO {

	@Autowired
	PhotoZoneMapper mapper;

	@Override
	public List<PhotoZoneDTO> getPhotozoneList() {
		return mapper.getPhotozoneList();
	}

	@Override
	public PhotoZoneDTO getPhotozone(String seq) {
		return mapper.getPhotozone(seq);
	}

	@Override
	public List<PhotoZoneImgDTO> getPhotozoneImgList(String seq) {
		return mapper.getPhotozoneImgList(seq);
	}
}
