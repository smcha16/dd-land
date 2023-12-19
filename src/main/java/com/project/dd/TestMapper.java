package com.project.dd;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.dd.login.domain.LoginDTO;

public interface TestMapper {

	@Select("select user_seq, pw from tblUser")
	ArrayList<LoginDTO> select();

	@Update("update tbluser set pw = #{pw} where user_seq = #{seq}")
	int update(@Param("seq") String seq, @Param("pw") String pw);

	@Select("SELECT count(*) from tblUser")
	int count();
	
}
