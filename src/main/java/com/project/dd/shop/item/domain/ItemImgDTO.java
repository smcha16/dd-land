package com.project.dd.shop.item.domain;

import lombok.Data;

/**
 * 아이템 이미지 정보를 담는 DTO 클래스입니다.
 * @author pega0
 *
 */
@Data
public class ItemImgDTO {
	private String item_img_seq;
	private String img;
	private String item_seq;
}
