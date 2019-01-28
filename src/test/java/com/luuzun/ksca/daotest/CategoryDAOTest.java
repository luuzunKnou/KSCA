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

import com.luuzun.ksca.persistence.CategoryDAO;

@RunWith(SpringJUnit4ClassRunner.class) //Spring loading
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) //Spring loading
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoryDAOTest {
	@Inject
	private CategoryDAO dao;
	private static Logger logger = LoggerFactory.getLogger(CategoryDAOTest.class);

	@Test
	public void test_01_Create() throws Exception{
	}
	
	@Test
	public void test_02_Update() throws Exception{
	}
	
	@Test
	public void test_03_Read() throws Exception{
	}
	
	@Test
	public void test_04_Delete() throws Exception{
	}
	
	@Test
	public void test_05_ListAll() throws Exception{
		logger.info(dao.listAll().toString());
	}
}
