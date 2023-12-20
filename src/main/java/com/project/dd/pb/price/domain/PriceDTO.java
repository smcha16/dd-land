package com.project.dd.pb.price.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Data;
/**
 * 티켓 객체 DTO 입니다.
 * @author green
 *
 */
@Data
public class PriceDTO {
		
		private String ticket_seq;
	    private String ticket_type;
	    private String person_type;
	    private String age;
	    @NotEmpty(message = "값을입력해주세요")
	    @Pattern(regexp = "^[0-9]+$", message = "숫자만 입력해주세요")
	    private String price;
	    
}