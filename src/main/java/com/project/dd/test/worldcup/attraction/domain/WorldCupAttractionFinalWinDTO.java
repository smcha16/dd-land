package com.project.dd.test.worldcup.attraction.domain;

import lombok.Data;

/**
 * 월드컵 어트랙션 최종 승리 DTO 클래스입니다.
 * 어트랙션의 최종 승리 정보를 담고 있습니다.
 * 
 * @author 이승원
 */
@Data
public class WorldCupAttractionFinalWinDTO {

    private String awc_final_win_seq;	// 어트랙션 월드컵 최종 승리 일련번호
    private String awc_final_win_count;	// 어트랙션 월드컵 최종 승리 횟수
    private String attraction_seq;		// 어트랙션 일련번호
    
}
