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

import com.luuzun.ksca.domain.Area;
import com.luuzun.ksca.persistence.AreaDAO;

@RunWith(SpringJUnit4ClassRunner.class) //Spring loading
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) //Spring loading
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AreaDAOTest {
	@Inject
	private AreaDAO dao;
	private static Logger logger = LoggerFactory.getLogger(AreaDAOTest.class);
	
	private String code="99-99-99";
	
	private Area area = new Area(code, "mcmoto", "TestCity", "99", "TestGu", "99",
	  "TestBr", "99");
	  
	private Area updateArea = new Area(code, "luuzun", "updateCity", "99", "TestGu",
	  "99", "TestBr", "00");
	
	@Test
	public void test_01_Create() throws Exception{
		dao.create(area);
	}
	
	@Test
	public void test_02_Update() throws Exception{
		dao.update(updateArea);
	}
	
	@Test
	public void test_03_Read() throws Exception{
		logger.info(dao.read(code).toString());
	}
	
	@Test
	public void test_04_Delete() throws Exception{
		dao.delete(code);
	}
	
	@Test
	public void test_05_ListAll() throws Exception{
		logger.info(dao.listAll().toString());
	}
	
	@Test
	public void test_06_readByManager() throws Exception{
		logger.info(dao.readByManager("mcmoto").toString());
	}
}
