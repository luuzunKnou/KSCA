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
	
	@Test
	public void test_01_Create() throws Exception{
		Area area = new Area();
		area.setCity("Test");
		area.setCityCode("99");
		area.setGu("Test");
		area.setGuCode("99");
		area.setCode();
		
		dao.create(area);
	}
	
	@Test
	public void test_02_Update() throws Exception{
		Area area = new Area();
		area.setCity("Up Test");
		area.setCityCode("88");
		area.setGu("Up Test");
		area.setGuCode("88");
		area.setCode();

		dao.update("99-99",area);
	}
	
	@Test
	public void test_03_Read() throws Exception{
		logger.info(dao.read("88-88").toString());
	}
	
	@Test
	public void test_04_Delete() throws Exception{
		dao.delete("99-99");
	}
	
	@Test
	public void test_05_ListAll() throws Exception{
		logger.info(dao.listAll().toString());
	}
}
