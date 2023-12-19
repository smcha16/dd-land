package com.project.dd.cart.mapper;

import java.util.List;

import com.project.dd.cart.domain.CartDTO;

public interface CartMapper {

	List<CartDTO> getUserList(String user_seq);

}
