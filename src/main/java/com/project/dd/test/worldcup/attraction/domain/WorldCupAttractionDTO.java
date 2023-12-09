package com.project.dd.test.worldcup.attraction.domain;

import lombok.Data;

/**
 * 월드컵 어트랙션 DTO 클래스입니다.
 * 어트랙션과 관련된 정보를 담고 있습니다.
 * 
 * @author 이승원
 */
@Data
public class WorldCupAttractionDTO {

    private String awc_seq;			// 어트랙션 월드컵 일련번호
    private String is_test;			// 테스트 여부
    private String attraction_seq;	// 어트랙션 일련번호
    
}
