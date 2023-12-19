package com.project.dd.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.cart.domain.CartDTO;
import com.project.dd.cart.repository.CartDAO;

@Service
public class CartService {

	@Autowired
	private CartDAO dao;
	
	public List<CartDTO> getUserList(String user_seq) {
		return dao.getUserList(user_seq);
	}

	public int delCart(String[] cart_seq) {
		int result = 0;
		
		for (String seq : cart_seq) {
			result += dao.delCart(seq);
		}
		
		return result;
	}

}
