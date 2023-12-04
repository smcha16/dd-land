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
	
	private List<AttractionImgDTO> imgList;
	//사용여부 확인하고 필요시 int imgCount 변수 생성하기
	
}
