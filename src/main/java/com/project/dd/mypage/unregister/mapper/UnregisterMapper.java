package com.project.dd.mypage.unregister.mapper;

public interface UnregisterMapper {

	boolean deleteMember(String email);

	int isValidUser(String email);

}
