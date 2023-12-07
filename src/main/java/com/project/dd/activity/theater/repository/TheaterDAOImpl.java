package com.project.dd.activity.theater.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.dd.activity.theater.mapper.TheaterMapper;

@Repository
public class TheaterDAOImpl {

	@Autowired
	TheaterMapper mapper;
}
