package com.project.dd.test.worldcup.attraction.domain;

import lombok.Data;

@Data
public class WorldCupAttractionWinDTO {

	// tblAWCWin
	private String awc_win_seq; // 어트랙션월드컵승리번호
	private String awc_match_count; // 어트랙션월드컵대결횟수
	private String awc_win_count; // 어트랙션월드컵승리횟수
	private String attraction_seq; // 어트랙션번호
	
}
