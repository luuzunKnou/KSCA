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

import com.luuzun.ksca.domain.Program;
import com.luuzun.ksca.persistence.ProgramDAO;

@RunWith(SpringJUnit4ClassRunner.class) //Spring loading
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) //Spring loading
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProgramDAOTest {
	@Inject
	private ProgramDAO dao;
	private static Logger logger = LoggerFactory.getLogger(ProgramDAOTest.class);
	
	@Test
	public void test_01_Create() throws Exception{
		Program program = new Program();
		
		program.setArea("03-01");
		program.setName("비누만들기");
		program.setCat1("3001");
		program.setCat2("101");
		program.setAgency("1");
		
		dao.create(program);
	}
	
	@Test
	public void test_02_Update() throws Exception{
		Program program = new Program();

		program.setCode("1");
		program.setArea("03-01");
		program.setName("TestUpdate");
		program.setCat1("3001");
		program.setCat2("101");
		program.setAgency("1");
		
		dao.update(program);
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
	public void test_07_readProgramJoinForList() throws Exception{
		logger.info(dao.readProgramJoinForList("03-01").toString());
	}
	
	@Test
	public void test_08_readProgramJoinByCode() throws Exception{
		logger.info(dao.readProgramJoinByCode("1").toString());
	}
}
