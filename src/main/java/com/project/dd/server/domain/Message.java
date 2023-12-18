package com.project.dd.server.domain;

public class Message {
	//클라이언트와 서버가 데이터를 주고받을 건데, 데이터를 받는 상자같은 느낌

	private String code;    //상태코드
	private String sender;  //보내는 사람
	private String receiver;  //받는 사람
	private String content;  //내용
	private String regdate;  //날짜
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "Message [code=" + code + ", sender=" + sender + ", receiver=" + receiver + ", content=" + content
				+ ", regdate=" + regdate + "]";
	}
}
