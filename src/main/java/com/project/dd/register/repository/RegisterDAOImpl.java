package com.project.dd.register.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.register.mapper.RegisterMapper;

import lombok.RequiredArgsConstructor;

@Primary
@Repository
@RequiredArgsConstructor
public class RegisterDAOImpl implements RegisterDAO{
	
	private final RegisterMapper mapper;


	@Override
	public int check(String email) {
		return mapper.check(email);
	}

}
