package com.luuzun.ksca;

import java.sql.Connection;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) //Spring loading
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) //Spring loading
public class DataSourceTest {
	@Inject //타입에 맞게 자동으로 주입
	private DataSource ds;
	
	@Test
	public void testConnection() throws Exception{
		try(Connection con = ds.getConnection()) {
			System.out.println(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
