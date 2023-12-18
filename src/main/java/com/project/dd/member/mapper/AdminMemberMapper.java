package com.project.dd.member.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate.Param;
import com.project.dd.register.domain.MemberDTO;

public interface AdminMemberMapper {


	List<MemberDTO> search(String query);
		
	

	int getTotalCount(String type);



	List<MemberDTO> getMemberList(Map<String, String> map);


}
