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

import com.luuzun.ksca.domain.Branch;
import com.luuzun.ksca.persistence.BranchDAO;

@RunWith(SpringJUnit4ClassRunner.class) //Spring loading
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) //Spring loading
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BranchDAOTest {
	@Inject
	private BranchDAO dao;
	private static Logger logger = LoggerFactory.getLogger(BranchDAOTest.class);
	
	@Test
	public void test_01_Create() throws Exception{
		Branch branch = new Branch();
		branch.setAreaCode("03-01");
		branch.setBranchCode("77");
		branch.setBranch("Test");
		dao.create(branch);
	}
	
	@Test
	public void test_02_Update() throws Exception{
		Branch branch = new Branch();
		branch.setAreaCode("03-01");
		branch.setBranchCode("66");
		branch.setBranch("Update");
		
		dao.update("03-01","77",branch);
	}
	
	@Test
	public void test_03_Read() throws Exception{
		logger.info(dao.read("03-01","66").toString());
	}
	
	@Test
	public void test_04_Delete() throws Exception{
		dao.delete("03-01","77");
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
