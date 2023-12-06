package com.project.dd.login.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.project.dd.login.domain.LoginDTO;

public interface LoginMapper {

	@Select("select * from tbluser where email = #{username}")
	LoginDTO read(@Param("username") String username);

}
