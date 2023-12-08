package com.project.dd.test.worldcup.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.project.dd.test.worldcup.course.service.WorldCupCourseService;

@Controller
public class AdminWorldCupCourseController {

    @Autowired
    private WorldCupCourseService courseService;
    
}
