package com.luuzun.ksca.daotest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.luuzun.ksca.domain.Schedule;
import com.luuzun.ksca.persistence.ScheduleDAO;

@RunWith(SpringJUnit4ClassRunner.class) //Spring loading
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) //Spring loading
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScheduleDAOTest {
	@Inject
	private ScheduleDAO dao;
	private static Logger logger = LoggerFactory.getLogger(ScheduleDAOTest.class);
	
	@Test
	public void test_01_Create() throws Exception{
		Schedule schedule = new Schedule();
		schedule.setSimpleDate("2019-01-01");
		schedule.setOffer("1");
		dao.create(schedule);
	}
	
	@Test
	public void test_02_Update() throws Exception{
		Schedule schedule = new Schedule();
		schedule.setSimpleDate("2019-02-02");
		schedule.setOffer("2");
		dao.update(schedule);
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
	public void test_06_ScheduleJoinforList() throws Exception{
		logger.info(dao.scheduleJoinforList("03-01","2","2019").toString());
	}
	
	@Test
	public void test_07_CreateMany() throws Exception{
		Schedule schedule1 = new Schedule();
		schedule1.setSimpleDate("2010-01-01");
		schedule1.setOffer("1");

		Schedule schedule2 = new Schedule();
		schedule2.setSimpleDate("2010-01-01");
		schedule2.setOffer("1");
		
		List<Schedule> list = new ArrayList<Schedule>();
		list.add(schedule1);
		list.add(schedule2);
		
		dao.createMany(list);
	}
	
}
