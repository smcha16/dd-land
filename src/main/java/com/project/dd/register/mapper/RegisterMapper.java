package com.project.dd.register.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.dd.register.domain.MemberDTO;

public interface RegisterMapper {

	@Select("select count(*) as cnt from tblUser where email = #{email}")
	int check(String email);
	@Update("insert into tblUser (user_seq, name, email, pw, tel, address, birth, lv, ing) values (seqtblUser.nextVal, #{name}, #{email},#{pw},#{tel},#{address}, TO_DATE(#{birth}, 'yyyy-mm-dd'), '1', 'Y')")
	int register(MemberDTO memberDTO);

}
