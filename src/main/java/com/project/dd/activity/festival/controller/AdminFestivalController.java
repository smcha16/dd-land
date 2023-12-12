package com.project.dd.activity.festival.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.activity.festival.service.FestivalService;

@Controller
@RequestMapping(value = "/admin/activity/festival")
public class AdminFestivalController {

	@Autowired
	private FestivalService service;
	
	@GetMapping(value = "/add.do")
	public String add(Model model) {

		return "admin/activity/festival/add";
	}
}
