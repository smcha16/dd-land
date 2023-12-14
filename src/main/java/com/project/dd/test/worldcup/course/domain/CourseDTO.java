package com.project.dd.test.worldcup.course.domain;

import lombok.Data;

@Data
public class CourseDTO {
	
	private String course_seq;
	private String name;
	private String img;
	
	// tblCWC 테스트 채택 여부
	private String is_test;
	
}
