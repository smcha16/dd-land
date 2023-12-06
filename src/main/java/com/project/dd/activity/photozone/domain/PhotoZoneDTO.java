package com.project.dd.activity.photozone.domain;

import java.util.List;

import lombok.Data;

@Data
public class PhotoZoneDTO {

	private String photozone_seq;
	private String name;
	private String time;
	private String info;
	
	//view 페이지용 이미지 1개 추출
	private String img;
	
	//tblPhotozoneImg
	private List<PhotoZoneImgDTO> imgList;
	
	//tblPhotozoneLocation
	private String photozone_location_seq;
	private String lat;
	private String lng;
	
	
}
