package com.project.dd.register.mapper;

import org.apache.ibatis.annotations.Select;

public interface RegisterMapper {

	@Select("select count(*) as cnt from tblUser where email = #{email}")
	int check(String email);

}
