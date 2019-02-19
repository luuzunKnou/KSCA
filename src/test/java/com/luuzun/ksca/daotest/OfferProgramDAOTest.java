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

import com.luuzun.ksca.domain.OfferProgram;
import com.luuzun.ksca.persistence.OfferProgramDAO;

@RunWith(SpringJUnit4ClassRunner.class) //Spring loading
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) //Spring loading
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OfferProgramDAOTest {
	@Inject
	private OfferProgramDAO dao;
	private static Logger logger = LoggerFactory.getLogger(OfferProgramDAOTest.class);
	
	@Test
	public void test_01_Create() throws Exception{
		OfferProgram OfferProgram = new OfferProgram();
		
		OfferProgram.setProgram("1");
		OfferProgram.setSimpleBeginDate("2019-02-01");
		OfferProgram.setSimpleEndDate("2019-02-01");
		OfferProgram.setSimpleRegMonth("2019-02-01");
		OfferProgram.setColor("000000");
		
		dao.create(OfferProgram);
	}
	
	@Test
	public void test_02_Update() throws Exception{
		OfferProgram OfferProgram = new OfferProgram();

		OfferProgram.setProgram("1");
		OfferProgram.setSimpleBeginDate("2019-02-01");
		OfferProgram.setSimpleEndDate("2019-02-01");
		OfferProgram.setSimpleRegMonth("2019-02-01");
		OfferProgram.setColor("111111");
		
		dao.update(OfferProgram);
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
	public void test_05_readOfferProgramJoinForList() throws Exception{
		logger.info(dao.readOfferProgramJoinForList("03-01","2019-02-01").toString());
	}

}
