package com.project.dd.activity.festival.domain;

import java.util.List;

import lombok.Data;

@Data
public class FestivalDTO {
	
	private String festival_seq;
	private String name;
	private String time;
	private String info;
	private String start_date;
	private String end_date;
	
	//view페이지용 이미지 1개 추출
	private String img;
	
	//tblFestivalImg
	private List<FestivalImgDTO> imgList;
	
	//tblFestivalLocation
	private String festival_location_seq;
	private String lat;
	private String lng;
	
}
