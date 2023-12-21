package com.project.dd.test.worldcup.attraction.domain;

import lombok.Data;

@Data
public class WorldCupAttractionRankDTO {

	private String attraction_seq; // 어트랙션번호
	private String name; // 어트랙션명
	private String info; // 어트랙션설명
	private String img; // 어트랙션이미지
	private String match_count; // 어트랙션월드컵대결횟수
	private String win_count; // 어트랙션월드컵승리횟수
	private String final_win_count; //어트랙션월드컵최종승리횟수
	private String win_rate; // 승률 (어트랙션월드컵승리횟수 / 어트랙션월드컵승리횟수)
	private String win_rate_per; // 승률 (100%)
	private String final_win_rate; // 최종승률 (어트랙션월드컵최종승리횟수 / 전체 테스트 횟수)
	private String final_win_rate_per;  // 최종승률 (100%)
	private String rnum; // 글번호
	
}
