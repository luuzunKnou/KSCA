package com.luuzun.ksca;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) //Spring loading
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) //Spring loading
public class MyBatisTest {
	@Inject
	private SqlSessionFactory sqlFactory;
	
	@Test
	public void testFactory() {
		System.out.println("  SqlSessionFactory : "+sqlFactory);
	}
	
	@Test
	public void testSession() throws Exception{
		try(SqlSession session = sqlFactory.openSession()) {
			System.out.println("  SqlSession : "+session);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
