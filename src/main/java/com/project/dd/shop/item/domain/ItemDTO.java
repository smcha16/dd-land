package com.project.dd.shop.item.domain;

import lombok.Data;

@Data
public class ItemDTO {
	private String item_seq;
	private String name;
	private String info;
	private String price;
	private String shop_seq;
	private String shop_name;
	private String img;
	
	private String ea;
	private String user_seq;
	private String cart_seq;
}
