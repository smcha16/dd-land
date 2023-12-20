package com.project.dd.test.worldcup.course.domain;

import lombok.Data;

@Data
public class CourseDTO {
	
	// tblCourse
	private String course_seq; // 코스 번호
	private String name; // 코스명
	private String img; // 코스이미지
	
	// tblCWC
	private String is_test; // 테스트 채택

	// tblCWCWin
	private String cwc_match_count; // 코스월드컵대결횟수
	private String cwc_win_count; // 코스월드컵승리횟수

	// tblCWCFinalWin
	private String cwc_final_win_count; // 코스월드컵최종승리횟수
	
}
