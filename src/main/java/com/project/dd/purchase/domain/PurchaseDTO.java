package com.project.dd.purchase.domain;

import lombok.Data;

/**
 * 구매 정보를 담는 DTO 클래스입니다.
 * @author pega0
 *
 */
@Data
public class PurchaseDTO {
	private String buy_seq;
	private String buy_date;
	private String ea;
	private String price;
	private String item_seq;
}
