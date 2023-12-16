package com.project.dd.mypage.unregister.repository;

import java.util.Map;

public interface UnregisterDAO {

	boolean deleteMember(String email);

	int isValidUser(String email);


}
