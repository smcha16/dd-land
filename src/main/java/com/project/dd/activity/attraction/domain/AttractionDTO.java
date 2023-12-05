package com.project.dd.activity.attraction.domain;

import java.util.List;

import lombok.Data;

@Data
public class AttractionDTO {
	
	private String attraction_seq;
	private String name;
	private String info;
	private String capacity;
	private String time;
	private String restriction;
	
	//view페이지용 이미지 1개 추출
	private String img;
	
	//tblAttractionImg
	private List<AttractionImgDTO> imgList;
	//사용여부 확인하고 필요시 int imgCount 변수 생성하기
	
	//tblAttractionClose
	private String close;
	
	//tblLocation
	private String location_seq;
	private String lat;
	private String lng;
	
	//tblAttractionLocation
	private String attraction_location_seq;
	
}
