package com.project.dd.register.domain;

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
	private String email;
	private String pw;
//	@Pattern(regexp = "^010\\d{8}$", message = "올바른 휴대폰 번호를 입력해주세요.")
	@NotEmpty(message = "전화번호를 입력해주세요.")
	private String tel;
	private String address;
	@NotEmpty(message = "생일을 입력주세요.")
	private String birth;
	private String lv;
	private String ing;

}
