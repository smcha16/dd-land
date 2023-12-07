package com.project.dd.activity.movie.domain;

import lombok.Data;

@Data
public class MoviePlayDTO {
	
	private String movie_play_seq;
	private String movie_seq;
	private String time;
	private String start_date;
	private String end_date;
	private String theater_seq;
	
	//tblTheater
	private String name;
	
	//tblTheaterLocation
	private String theater_location_seq;
	private String lat;
	private String lng;
	
	//운휴 여부(y: 운휴, n: 운영)
	private String close;
}
