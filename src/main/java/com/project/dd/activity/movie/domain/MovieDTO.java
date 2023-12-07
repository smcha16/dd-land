package com.project.dd.activity.movie.domain;

import lombok.Data;

@Data
public class MovieDTO {

	private String movie_seq;
	private String name;
	private String story;
	private String runningtime;
	private String img;
	private String preview;
	
	//tblMoviePlay
	private String movie_play_seq;
	private String time;
	private String start_date;
	private String end_date;
	
	//tblTheater
	private String theater_seq;
	private String theater_name;
	
	//tblTheaterLocation
	private String theater_location_seq;
	private String lat;
	private String lng;
}
