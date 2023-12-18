package com.project.dd.shop.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dd.shop.item.domain.ItemDTO;
import com.project.dd.shop.item.service.ItemService;

@RestController
public class RestItemController {

	@Autowired
	private ItemService service;
	
	@PostMapping(value = "/member/shop/gift-shop/item/cart")
	public int cart(@RequestBody ItemDTO dto) {

		return service.addCart(dto);
	}
	
}
