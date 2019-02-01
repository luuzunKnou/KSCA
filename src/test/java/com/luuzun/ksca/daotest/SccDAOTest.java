package com.luuzun.ksca.daotest;

import java.util.Date;

import javax.inject.Inject;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.luuzun.ksca.domain.SCC;
import com.luuzun.ksca.persistence.SccDAO;

@RunWith(SpringJUnit4ClassRunner.class) //Spring loading
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) //Spring loading
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SccDAOTest {
	@Inject
	private SccDAO dao;
	private static Logger logger = LoggerFactory.getLogger(SccDAOTest.class);
	
	@Test
	public void test_01_Create() throws Exception{
		SCC scc = new SCC("99-99-99-999", "TestSCC", "mcmoto","03-01-99");
		dao.create(scc);
	}
	
	@Test
	public void test_02_Update() throws Exception{
		SCC updateScc = new SCC("99-99-999", "TestDong", "UpdateSCC", "UpdateAdd", new Date(), 
				(float)999.9, (float)999.9, 99, 9, 9, "°ø¼³", "999-9999-9999", 
				"Tester", "053-999-9999", "mcmoto", "03-01-99");
		dao.update(updateScc);
	}
	
	@Test
	public void test_03_Read() throws Exception{
		logger.info(dao.read("99-99-999").toString());
	}
	
	@Test
	public void test_04_Delete() throws Exception{
		dao.delete("99-99-999");
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
