package com.project.dd.login.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.project.dd.login.domain.LoginDTO;
import com.project.dd.register.domain.MemberDTO;

public interface LoginMapper {

	@Select("select user_seq, name, email, pw, tel, address, birth,  'ROLE_' || lv as auth, ing from tbluser where email = #{username}")
	LoginDTO read(@Param("username") String username);
	@Select("select * from tbluser")
	List<MemberDTO> list();

}
