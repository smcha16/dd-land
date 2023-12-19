package com.project.dd.activity.photozone.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.activity.photozone.domain.PhotoZoneDTO;
import com.project.dd.activity.photozone.domain.PhotoZoneImgDTO;

public interface PhotoZoneMapper {

	//(운영종료 제외) 포토존 List
	List<PhotoZoneDTO> getPhotozoneList(Map<String, String> map);
	
	//포토존 상세 정보
	PhotoZoneDTO getPhotozone(String seq);
	
	//포토존 이미지 List
	List<PhotoZoneImgDTO> getPhotozoneImgList(String seq);

	int getTotalCount();

	List<PhotoZoneImgDTO> getAllPhotozoneImgList();

	int addPhotozone(PhotoZoneDTO dto);

	int checkLocationDuplication(PhotoZoneDTO dto);

	int checkNameDuplication(PhotoZoneDTO dto);

	String getPhotozoneSeq();

	int addPhotozoneLocation(PhotoZoneDTO dto);

	int addPhotozoneImg(PhotoZoneImgDTO idto);

	int editPhotozone(PhotoZoneDTO dto);

	int editPhotozoneLocation(PhotoZoneDTO dto);

	int delPhotozoneImg(String seq);

	int countPhotozoneImg(String seq);

	int delPhotozoneImgByImgSeq(String imgseq);

	int countPhotozoneLocation(String seq);

	int delPhotozoneLocation(String seq);

	int delPhotozone(String seq);
}
