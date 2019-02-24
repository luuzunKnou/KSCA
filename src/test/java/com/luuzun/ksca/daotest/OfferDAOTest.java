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

import com.luuzun.ksca.domain.Offer;
import com.luuzun.ksca.persistence.OfferDAO;

@RunWith(SpringJUnit4ClassRunner.class) //Spring loading
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) //Spring loading
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OfferDAOTest {
	@Inject
	private OfferDAO dao;
	private static Logger logger = LoggerFactory.getLogger(OfferDAOTest.class);
	
	@Test
	public void test_01_Create() throws Exception{
		Offer offer = new Offer();
		offer.setAreaCode("03-01");
		offer.setBranchCode("99");
		offer.setSccCode("001");
		offer.setProgram("1");
		offer.setMonthlyOper(8);
		offer.setActiveUser(10);
		dao.create(offer);
	}
	
	@Test
	public void test_02_Update() throws Exception{
		Offer offer = new Offer();
		offer.setAreaCode("03-01");
		offer.setBranchCode("99");
		offer.setSccCode("002");
		offer.setProgram("2");
		offer.setMonthlyOper(8);
		offer.setActiveUser(10);
		dao.update(offer);
	}
	
	@Test
	public void test_03_Read() throws Exception{
		logger.info(dao.read("1").toString());
	}
	
	@Test
	public void test_04_Delete() throws Exception{
		dao.delete("3");
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
	public void test_07_readForExistCheck() throws Exception{
		logger.info(dao.readForExistCheck("03-01","99","002","1").toString());
	}
	
	@Test
	public void test_08_updateMonthlyOper() throws Exception{
		dao.updateMonthlyOper("1",10);
	}

	@Test
	public void test_09_readByRegMonth() throws Exception{
		logger.info(dao.readByRegMonth("03-01","2019-02-01").toString());
	}
	
}

