package com.project.dd.shop.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dd.shop.item.domain.ItemDTO;
import com.project.dd.shop.item.service.ItemService;

/**
 * 상품 관련 RESTful API를 처리하는 컨트롤러 클래스입니다.
 * @author pega0
 *
 */
@RestController
public class RestItemController {

	@Autowired
	private ItemService service;
	
	/**
	 * 상품을 장바구니에 추가하는 메서드입니다.
	 *
	 * @param dto 상품 정보를 담은 DTO 객체
	 * @return 상품 추가 결과를 반환 (성공 시 1, 실패 시 0)
	 */
	@PostMapping(value = "/member/shop/gift-shop/item/cart")
	public int cart(@RequestBody ItemDTO dto) {

		return service.addCart(dto);
	}
	
}
