package com.project.dd.pb.price.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.dd.pb.price.domain.PriceDTO;

public class AdminController {
	
	@GetMapping(value="/ex06.do")
	public List<PriceDTO> test() {
		
		List<PriceDTO> list = new ArrayList<PriceDTO>();
		
		list.add();
		
		return list;
	}

}
