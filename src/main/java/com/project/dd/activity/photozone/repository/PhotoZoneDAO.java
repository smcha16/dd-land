package com.project.dd.activity.photozone.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.activity.photozone.domain.PhotoZoneDTO;
import com.project.dd.activity.photozone.domain.PhotoZoneImgDTO;

public interface PhotoZoneDAO {

	List<PhotoZoneDTO> getPhotozoneList(Map<String, String> map);

	PhotoZoneDTO getPhotozone(String seq);

	List<PhotoZoneImgDTO> getPhotozoneImgList(String seq);

	int getTotalCount();

}
