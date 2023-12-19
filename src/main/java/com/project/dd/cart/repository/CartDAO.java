package com.project.dd.cart.repository;

import java.util.List;

import com.project.dd.cart.domain.CartDTO;

public interface CartDAO {

	List<CartDTO> getUserList(String user_seq);

}
