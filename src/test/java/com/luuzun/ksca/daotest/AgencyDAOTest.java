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

import com.luuzun.ksca.domain.Agency;
import com.luuzun.ksca.persistence.AgencyDAO;

@RunWith(SpringJUnit4ClassRunner.class) //Spring loading
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) //Spring loading
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AgencyDAOTest {
	@Inject
	private AgencyDAO dao;
	private static Logger logger = LoggerFactory.getLogger(AgencyDAOTest.class);
	
	@Test
	public void test_01_Create() throws Exception{
		Agency agency = new Agency();
		agency.setArea("03-01");
		agency.setName("Áß±¸Ã»");
		agency.setTel("010-4487-1123");
		agency.setManager("±Ýº¹Èñ");
		
		logger.info("lastIDXxx: "+String.valueOf(dao.create(agency)));
	}
	
	@Test
	public void test_02_Update() throws Exception{
		Agency agency = new Agency();
		
		agency.setCode("1");
		agency.setArea("03-01");
		agency.setName("Áß±¸Ã¼À°È¸");
		agency.setTel("010-4487-1123");
		agency.setManager("±Ýº¹Èñ");
		
		dao.update(agency);
	}
	
	@Test
	public void test_03_Read() throws Exception{
		logger.info(dao.read("1").toString());
	}
	
	@Test
	public void test_04_Delete() throws Exception{
		dao.delete("4");
	}
	
	@Test
	public void test_05_ListAll() throws Exception{
		logger.info(dao.listAll().toString());
	}
	
	@Test
	public void test_06_readByAreaCode() throws Exception{
		logger.info(dao.readByAreaCode("03-01").toString());
	}
}
