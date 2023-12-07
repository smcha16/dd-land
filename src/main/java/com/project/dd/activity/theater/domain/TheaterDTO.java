package com.project.dd.activity.theater.domain;

import lombok.Data;

@Data
public class TheaterDTO {

	private String theater_seq;
	private String name;
	
	//tblTheaterLocation
	private String theater_location_seq;
	private String lat;
	private String lng;
}
