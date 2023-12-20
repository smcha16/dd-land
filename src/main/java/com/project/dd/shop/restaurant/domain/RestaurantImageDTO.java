package com.project.dd.shop.restaurant.domain;

import lombok.Data;

/**
 * 레스토랑 이미지 정보를 담는 DTO 클래스입니다.
 * @author pega0
 *
 */
@Data
public class RestaurantImageDTO {
	private String image_seq;
	private String img;
	private String pseq;
	
	private String restaurant_img_seq;
	private String restaurant_seq;
}
