package com.test.java.db;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.project.dd.TestMapper;
import com.project.dd.login.domain.LoginDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class HikariCPTest {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private TestMapper mapper;
	
	@Test 
	public void testUpdatePw() {
		assertNotNull(mapper);
		
		ArrayList<LoginDTO> list = mapper.select();
		
		int count = mapper.count();
		
		for (LoginDTO dto : list) {
			String pw = encoder.encode(dto.getPw());
			String seq = dto.getUser_seq();
			
			int result = mapper.update(seq, pw);
			
			if (result == 1) {
				System.out.println("변경");
			} else {
				System.out.println("변경 안됨");
			}
		}
		
	}
	
}
