package com.project.dd.close.attraction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.dd.close.attraction.service.CloseAttractionService;

@Controller
public class AdminCloseAttractionController {
	
	@Autowired private CloseAttractionService closeAttrService;
	 
	@GetMapping(value = "/admin/close/attraction/view.do") 
	public String view(Model model) {
	 
	return "admin/close/attraction/view"; }
	 

}
