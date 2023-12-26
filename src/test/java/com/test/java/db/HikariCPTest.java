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

/**
 * HikariCP 라이브러리를 이용해 JDBC 커넥션 풀이 관리되는지 Test하는 클래스입니다.
 * 
 * @author pega0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/security-context.xml" })
public class HikariCPTest {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private TestMapper mapper;

	/**
	 * 데이터베이스에서 사용자 목록을 가져와 각 사용자의 비밀번호를 암호화하여 업데이트하는 테스트를 수행합니다.
	 */
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
