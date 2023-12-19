package com.project.dd.cart.domain;

import lombok.Data;

@Data
public class CartDTO {
	private String user_cart_seq;
	private String user_seq;
	private String cart_seq;
	private String item_seq;
	private String ea;
	private String name;
	private int price;
}
