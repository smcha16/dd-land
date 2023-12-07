package com.project.dd.activity.photozone.mapper;

import java.util.List;

import com.project.dd.activity.photozone.domain.PhotoZoneDTO;
import com.project.dd.activity.photozone.domain.PhotoZoneImgDTO;

public interface PhotoZoneMapper {

	//(운영종료 제외) 포토존 List
	List<PhotoZoneDTO> getPhotozoneList();
	
	//포토존 상세 정보
	PhotoZoneDTO getPhotozone(String seq);
	
	//포토존 이미지 List
	List<PhotoZoneImgDTO> getPhotozoneImgList(String seq);
}
