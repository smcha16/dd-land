package com.project.dd.activity.attraction.domain;

import lombok.Data;

@Data
public class AttractionLocationDTO {

	private String attraction_location_seq;
	private String attraction_seq;
	private String location_seq;
	
	private String lat;
	private String lng;
}
