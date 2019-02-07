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
		SCC scc = new SCC();
		scc.setAreaCode("03-01");
		scc.setBranchCode("01");
		scc.setSccCode("999");
		scc.setName("Test");
		dao.create(scc);
		
		SCC scc2 = new SCC();
		scc2.setAreaCode("03-01");
		scc2.setBranchCode("01");
		scc2.setSccCode("888");
		scc2.setName("Test");
		dao.create(scc2);
	}
	
	@Test
	public void test_02_Update() throws Exception{
		SCC scc = new SCC();
		scc.setAreaCode("03-02");
		scc.setBranchCode("02");
		scc.setSccCode("888");
		scc.setName("upTest");
		scc.setDong("upTest");
		
		dao.update("03-01","01","999",scc);
	}
	
	@Test
	public void test_03_Read() throws Exception{
		logger.info(dao.read("03-01","01","888").toString());
	}
	
	@Test
	public void test_04_Delete() throws Exception{
		dao.delete("03-01","01","999");
	}
	
	@Test
	public void test_05_ListAll() throws Exception{
		logger.info(dao.listAll().toString());
	}
	
	@Test
	public void test_06_readByAreaCode() throws Exception{
		logger.info(dao.readByAreaCode("03-01").toString());
	}
	
	@Test
	public void test_07_readByBranchCode() throws Exception{
		logger.info(String.valueOf(dao.readByBranchCode("03-01", "99")));
	}
	
}
