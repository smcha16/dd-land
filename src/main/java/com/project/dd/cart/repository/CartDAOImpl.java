package com.project.dd.cart.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.cart.domain.CartDTO;
import com.project.dd.cart.mapper.CartMapper;

@Repository
@Primary
public class CartDAOImpl implements CartDAO {

	@Autowired
	private CartMapper mapper;
	
	@Override
	public List<CartDTO> getUserList(String user_seq) {
		return mapper.getUserList(user_seq);
	}
	
	@Override
	public int delCart(String seq) {
		return mapper.delCart(seq);
	}
	
}
