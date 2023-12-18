package com.project.dd.activity.movie.domain;

import java.util.List;

import com.project.dd.activity.movieplay.domain.MoviePlayDTO;

import lombok.Data;

@Data
public class MovieDTO {

	private String movie_seq;
	private String name;
	private String story;
	private String runningtime;
	private String img;
	private String preview;
	
	//영화 상영
	private List<MoviePlayDTO> moviePlayList;
	
}
