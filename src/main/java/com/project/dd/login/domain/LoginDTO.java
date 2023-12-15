package com.project.dd.login.domain;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class LoginDTO {

	private String user_seq;
	private String name;
	@NotEmpty(message = "아이디를 입력해주세요")
	private String email;
	@NotEmpty(message = "비밀번호를 입력해주세요")
	private String pw;
	private String tel;
	private String address;
	private String birth;
	private String auth;
	private String ing;
	
}
