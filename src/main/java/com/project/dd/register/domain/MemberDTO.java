package com.project.dd.register.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


import lombok.Data;
/**
 * 회원 DTO 클래스입니다.
 * @author green
 *
 */
@Data
public class MemberDTO {
	
	private String user_seq;
	@NotEmpty(message = "이름을 입력해주세요.")
	private String name;
	@NotEmpty(message = "이메일을 입력해주세요.")
	@Email(message = "올바른 이메일 주소를 입력해주세요.")
	private String email;
	private String pw;	
	@Pattern(regexp = "^010-\\d{3,4}-\\d{4}$", message = "올바른 전화번호 형식(010-1234-5678)으로 입력해주세요.")
	@NotEmpty(message = "전화번호를 입력해주세요.")
	private String tel;
	@NotEmpty(message = "주소를 입력해주세요.")
	private String address;
	@NotEmpty(message = "생일을 입력주세요.")
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "올바른 생일 형식(YYYY-MM-DD)으로 입력해주세요.")
	private String birth;
	private String lv;
	@Pattern(regexp = "^[YN]$", message = "Y 또는 N 중 하나를 입력해주세요.")
	@NotEmpty
	private String ing;

}
