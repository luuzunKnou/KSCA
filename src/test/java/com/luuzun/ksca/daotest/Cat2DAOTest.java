package com.luuzun.ksca.daotest;

import javax.inject.Inject;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.luuzun.ksca.domain.Cat2;
import com.luuzun.ksca.persistence.Cat2DAO;

@RunWith(SpringJUnit4ClassRunner.class) //Spring loading
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) //Spring loading
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Cat2DAOTest {
	@Inject
	private Cat2DAO dao;
	private static Logger logger = LoggerFactory.getLogger(Cat2DAOTest.class);
	
	@Test
	public void test_01_Create() throws Exception{
		Cat2 cat2 = new Cat2();
		cat2.setCode("111");
		cat2.setCat1("3001");
		cat2.setName("Test");
		
		dao.create(cat2);
	}
	
	@Test
	public void test_02_Update() throws Exception{
		Cat2 cat2 = new Cat2();
		cat2.setCode("222");
		cat2.setCat1("3002");
		cat2.setName("Test2");
		
		dao.update("111","3001",cat2);
	}
	
	@Test
	public void test_03_Read() throws Exception{
		logger.info(dao.read("222","3002").toString());
	}
	
	@Test
	public void test_04_Delete() throws Exception{
		dao.delete("222","3002");
	}
	
	@Test
	public void test_05_ListAll() throws Exception{
		logger.info(dao.listAll().toString());
	}
}
