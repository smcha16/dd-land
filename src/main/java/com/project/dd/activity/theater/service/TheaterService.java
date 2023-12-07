package com.project.dd.activity.theater.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.activity.theater.repository.TheaterDAO;

@Service
public class TheaterService {

	@Autowired
	TheaterDAO dao;
}
