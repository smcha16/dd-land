package com.project.dd.test.mbti.domain;

import lombok.Data;

@Data
public class MBTIDTO {

	private String mbti_seq; // MBTI 번호
	private String result; // 결과명
	private String name; // MBTI명
	private String mbti_img; // MBTI이미지
	private String course_seq; // 코스번호
	private String attraction_seq; // 어트랙션 번호

	// tblAttraction
	private String attraction_name; // 어트랙션 이름

	// tblAttractionImg
	private String attraction_img; // 어트랙션 이미지

	// tblCourse
	private String course_name; // 코스 이름
	private String course_img; // 코스 이미지

}
