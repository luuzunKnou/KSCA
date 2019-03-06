package com.luuzun.ksca;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class MySQLConnectionTest {
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://ksca.c6y4tf0rgrew.ap-northeast-2.rds.amazonaws.com:3306/ksca?useSSL=false";
	private static final String USER = "luuzun";
	private static final String PASSWORD = "luuzun.ksca";
	
	@Test
	public void testConncetion() throws Exception{
		Class.forName(DRIVER);
		try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
			System.out.println(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
