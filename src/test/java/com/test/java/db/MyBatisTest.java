package com.test.java.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zaxxer.hikari.HikariDataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MyBatisTest {

	@Test
	public void testConnection() {
		
		try {
			
			// 오라클 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin@localhost:1521:xe", "dd", "pass");
			
			assertNotNull(conn); // DB 연결 성공 시 True
			
			assertEquals("DB 연결", false, conn.isClosed()); // 기대값이 같은지를 검증하여 conn.isClose()가 false와 같을 경우 성공
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Autowired
	private HikariDataSource dataSource;
	
	@Test
	public void testConnectionPool() {
		
		// 직접 Connection을 생성하지 않고 Connection Pool을 통해 Connection이 잘 생성되는지 테스트
		
		assertNotNull(dataSource);
		
		try {
			
			Connection conn = dataSource.getConnection();
			assertEquals(false, conn.isClosed());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Autowired
	private SqlSessionTemplate template;
	
	@Test
	public void testQuery() {
		
		int count = template.selectOne("rest.test");
		
		assertNotNull(template);
		assertEquals(5, count);
		
	}
	
}
