package com.project.dd;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.dd.login.domain.LoginDTO;

public interface TestMapper {

	@Select("select * from tbluser")
	List<LoginDTO> select();

	@Update("update tbluser set pw = #{pw} where user_seq = #{seq}")
	int update(@Param("seq") String seq, @Param("pw") String pw);
	
}
