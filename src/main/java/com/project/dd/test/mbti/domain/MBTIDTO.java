package com.project.dd.test.mbti.domain;

import lombok.Data;

@Data
public class MBTIDTO {

	private String mbti_seq; // MBTI 번호
	private String result; // 결과명
	private String mbti; // MBTI명
	private String mbti_img; // MBTI이미지
	private String course_seq; // 코스번호
	private String attraction_seq; // 어트랙션 번호
	
}
