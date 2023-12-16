package com.project.dd.register.repository;

import com.project.dd.register.domain.MemberDTO;

public interface RegisterDAO {

	int check(String email);

	int register(MemberDTO memberDTO);

}
