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

import com.luuzun.ksca.domain.Manager;
import com.luuzun.ksca.domain.Permission;
import com.luuzun.ksca.persistence.ManagerDAO;

@RunWith(SpringJUnit4ClassRunner.class) //Spring loading
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) //Spring loading
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ManagerDAOTest {
	@Inject
	private ManagerDAO dao;
	private static Logger logger = LoggerFactory.getLogger(ManagerDAOTest.class);
	
	String id = "Tester";
	String password = "1234";
			
	Manager manager = new Manager("Tester", "1234", "Tester", "1234-1234", 
			"Tester@test.com", true, true, Permission.MANAGER, "03-01");
	
	Manager updateManager = new Manager("Tester", "4321", "Update", "1111-1234", 
			"Update@test.com", true, true, Permission.MANAGER, "03-01"); 
	
	Manager newManager = new Manager("Tester2", "4321", "Update", "1111-1234", 
			"Update@test.com", true, true, Permission.MANAGER, "03-01");
	
	@Test
	public void test_01_Create() throws Exception{
		dao.create(manager);
		dao.create(newManager);
	}
	
	@Test
	public void test_02_Update() throws Exception{
		dao.update(updateManager);
	}
	
	@Test
	public void test_03_Read() throws Exception{
		logger.info("***read"+dao.read(id).toString());
	}
	
	@Test
	public void test_04_Delete() throws Exception{
		dao.delete(id);
	}
	
	@Test
	public void test_05_ListAll() throws Exception{
		logger.info("***listAll"+dao.listAll().toString());
	}
	
	@Test
	public void test_06_ReadForLogin() throws Exception{
		logger.info("***readForLogin"+dao.readForLogin("luuzun", "1234").toString());
	}
	
	@Test
	public void test_07_ReadWaitingManager() throws Exception{
		logger.info("***readWaitingManager"+dao.readWaitingManager().toString());
	}
	
	@Test
	public void test_08_UpdateApproveManager() throws Exception{
		dao.updateApproveManager(newManager.getId());
	}
	
	@Test
	public void test_09_readManagerHasArea() throws Exception{
		logger.info("***readManagerHasArea"+dao.readManagerHasArea("mcmoto").toString());
	}
	
	@Test
	public void test_10_leave() throws Exception{
		dao.leave("mcmoto");
	}
	
	@Test
	public void test_11_rejosin() throws Exception{
		dao.rejoin("mcmoto");
	}
}
