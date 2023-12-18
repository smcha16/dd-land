package com.project.dd.member.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.register.domain.MemberDTO;

public interface AdminMemberDAO {

	List<MemberDTO> search(String query);


	int getTotalCount(String type);


	List<MemberDTO> getMemberList(Map<String, String> map);

}

