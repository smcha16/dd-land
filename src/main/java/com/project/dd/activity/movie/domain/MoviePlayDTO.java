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
}
