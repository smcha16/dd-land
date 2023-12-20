package com.project.dd.shop.giftshop.domain;

import lombok.Data;

/**
 * 기프트샵 정보를 담는 DTO 클래스입니다.
 * @author pega0
 *
 */
@Data
public class ShopDTO {
	private String shop_seq;
	private String name;
	private String time;
	private String info;
	private String tel;
	private String close;
	private String lat;
	private String lng;
	private String img;
}
