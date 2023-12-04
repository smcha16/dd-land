package com.test.java.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class JDBCTest {

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
	
}
