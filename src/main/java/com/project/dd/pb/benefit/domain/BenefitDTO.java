package com.project.dd.pb.benefit.domain;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Data;
/**
 * 혜택 객체입니다.
 * @author 김형우
 *
 */
@Data
public class BenefitDTO {
	
	 private String benefit_seq;
	 	@NotEmpty(message = "이름을 입력해주세요.")
	    private String name;
	    private String type;
	    @NotEmpty(message = "시작일을 선택해주세요.")
	    private String start_date;
	    @NotEmpty(message = "종료일을 선택해주세요.")
	    private String end_date;
	    @NotEmpty(message = "할인율을 입력해주세요.")
	    @DecimalMax(value = "100", message = "최대 100까지 입력 가능합니다.")
	    @Pattern(regexp = "^[0-9]*$", message = "숫자만 입력 가능합니다.")
	    private String discount_rate;
	    @NotEmpty(message = "이미지를 선택해주세요.")
	    private String img; 

}





