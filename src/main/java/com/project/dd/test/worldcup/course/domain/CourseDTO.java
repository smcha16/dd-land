package com.project.dd.test.worldcup.course.domain;

import lombok.Data;

@Data
public class CourseDTO {
	
	private String course_seq;
	private String name;
	private String img;
	
	// tblCWC 테스트 채택 여부
	private String is_test;

	// tblCWCWin
	private String cwc_match_count;
	private String cwc_win_count;

	// tblCWCFinalWin
	private String cwc_final_win_count;
	
}
