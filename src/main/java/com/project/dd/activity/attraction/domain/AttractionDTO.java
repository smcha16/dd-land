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
	//private List<AttractionCloseDTO> closeList;
	private String close;
	
	//tblAttractionLocation
	//private AttractionLocationDTO location; //이건 되면 하고 안되면 말고
	//얜 필요없어 locationDTO가 필요해
	//? 그럼 location_seq 변수도 필요함?
	
	private LocationDTO location;
	
	
}
