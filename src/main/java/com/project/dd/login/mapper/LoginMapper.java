package com.project.dd.login.mapper;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.dd.login.domain.LoginDTO;
import com.project.dd.register.domain.MemberDTO;

public interface LoginMapper {

	@Select("select user_seq, name, email, pw, tel, address, birth,  'ROLE_' || lv as auth, ing from tbluser where email = #{username}")
	LoginDTO read(@Param("username") String username);
	@Select("select user_seq, name, email, pw, tel, address, TO_CHAR(birth,'YYYY-MM-DD') AS birth, lv, ing from TBLUSER")
	List<MemberDTO> list();
	@Select("select * from TBLUSER where NAME = #{name} and birth = #{birth} and tel = #{tel}")
	MemberDTO findId(@Valid MemberDTO memberDTO);
	@Select("select * from TBLUSER where user_seq = #{user_seq}")
	MemberDTO findMember(String user_seq);
	@Update("update TBLUSER set pw = #{pw} where user_seq = #{user_seq}")
	int changePw(@Param("pw") String pw, @Param("user_seq") String user_seq);
	@Update("Update TBLUSER set pw = #{pw} where user_seq = #{user_seq}")
	void encoderPw(MemberDTO memberDTO);
	@Select("select user_seq from TBLUSER where email= #{email}")
	String findSeq(@Valid MemberDTO memberDTO);
	
	
}
