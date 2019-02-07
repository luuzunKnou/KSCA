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

import com.luuzun.ksca.domain.Cat1;
import com.luuzun.ksca.persistence.Cat1DAO;

@RunWith(SpringJUnit4ClassRunner.class) //Spring loading
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) //Spring loading
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Cat1DAOTest {
	@Inject
	private Cat1DAO dao;
	private static Logger logger = LoggerFactory.getLogger(Cat1DAOTest.class);
	
	@Test
	public void test_01_Create() throws Exception{
		Cat1 cat1 = new Cat1();
		cat1.setCode("1111");
		cat1.setName("Test");
		
		dao.create(cat1);
	}
	
	@Test
	public void test_02_Update() throws Exception{
		Cat1 cat1 = new Cat1();
		cat1.setCode("2222");
		cat1.setName("Test2");
		
		dao.update("1111",cat1);
	}
	
	@Test
	public void test_03_Read() throws Exception{
		logger.info(dao.read("2222").toString());
	}
	
	@Test
	public void test_04_Delete() throws Exception{
		dao.delete("2222");
	}
	
	@Test
	public void test_05_ListAll() throws Exception{
		logger.info(dao.listAll().toString());
	}
}
