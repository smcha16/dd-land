package com.project.dd.member.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Update;

import com.project.dd.register.domain.MemberDTO;


public interface AdminMemberMapper {


	List<MemberDTO> search(String query);
		
	

	int getTotalCount(String type);



	List<MemberDTO> getMemberList(Map<String, String> map);


	@Update("update tbluser set ing = 'N' where user_seq = #{user_seq}")
	int del(String user_seq);


	@Update("update tbluser set name = #{name} , email = #{email} , tel = #{tel} ,address = #{address} , birth = #{birth},ing = #{ing} where user_seq = #{user_seq}")
	int edit(MemberDTO memberDTO);


}
