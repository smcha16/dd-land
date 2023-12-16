package com.project.dd.close.restaurant.domain;

import lombok.Data;

@Data
public class CloseRestaurantDTO {
	private String restaurant_close_seq;
	private String start_date;
	private String end_date;
	private String restaurant_seq;
	
	private String name;
}
