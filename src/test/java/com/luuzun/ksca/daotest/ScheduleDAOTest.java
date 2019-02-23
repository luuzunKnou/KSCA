package com.luuzun.ksca.daotest;

import java.util.ArrayList;
import java.util.Calendar;
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
	
//	@Test
//	public void test_01_Create() throws Exception{
//		Schedule schedule = new Schedule();
//		schedule.setSimpleDate("2019-01-01");
//		schedule.setOffer("1");
//		dao.create(schedule);
//	}
//	
//	@Test
//	public void test_02_Update() throws Exception{
//		Schedule schedule = new Schedule();
//		schedule.setSimpleDate("2019-02-02");
//		schedule.setOffer("2");
//		dao.update(schedule);
//	}
//	
//	@Test
//	public void test_03_Read() throws Exception{
//		logger.info(dao.read("1").toString());
//	}
//	
//	@Test
//	public void test_04_Delete() throws Exception{
//		dao.delete("3");
//	}
//	
//	@Test
//	public void test_05_ListAll() throws Exception{
//		logger.info(dao.listAll().toString());
//	}
//	
//	@Test
//	public void test_06_ScheduleJoinforList() throws Exception{
//		logger.info(dao.scheduleJoinforList("03-01","2","2019").toString());
//	}
//	
//	@Test
//	public void test_07_CreateMany() throws Exception{
//		Schedule schedule1 = new Schedule();
//		schedule1.setSimpleDate("2010-01-01");
//		schedule1.setOffer("1");
//
//		Schedule schedule2 = new Schedule();
//		schedule2.setSimpleDate("2010-01-01");
//		schedule2.setOffer("1");
//		
//		List<Schedule> list = new ArrayList<Schedule>();
//		list.add(schedule1);
//		list.add(schedule2);
//		
//		dao.createMany(list);
//	}
//	
//	@Test
//	public void test_08_readMonthList() throws Exception{
//		logger.info(dao.readMonthList("03-01").toString());
//	}
	
//	@Test
//	public void test_08_readMonthList() throws Exception{
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.YEAR, 2019);
//		cal.set(Calendar.MONTH, 2-1);
//		cal.set(Calendar.DATE, 1);
//		int monthCnt = cal.get(Calendar.WEEK_OF_MONTH);
//		int date = cal.get(Calendar.DAY_OF_WEEK);
//		//요일 (1: 일요일)
//		System.out.println(monthCnt +":"+date);
//		Calendar destCal = Calendar.getInstance();
//		destCal.set(Calendar.YEAR, 2019);
//		destCal.set(Calendar.MONTH, 3-1);
//
//		List<Calendar> cals = new ArrayList<>();
//		
//		System.out.println("Max: "+destCal.getActualMaximum(Calendar.DATE));
//		for(int i=1; i<=destCal.getActualMaximum(Calendar.DATE); i++) {//destMonth 1~31일
//			destCal.set(Calendar.DATE, i);
//			System.out.println(destCal.get(Calendar.DATE));
//
//			if(destCal.get(Calendar.WEEK_OF_MONTH)==monthCnt 
//					&& destCal.get(Calendar.DAY_OF_WEEK)==date) { //주, 요일이 같은 날
//
//				Calendar addCal = Calendar.getInstance();
//				addCal.set(Calendar.YEAR, 2019);
//				addCal.set(Calendar.MONTH, 3-1);
//				addCal.set(Calendar.DATE, i);
//				System.out.println("add : "+addCal.get(Calendar.DATE));
//				cals.add(addCal);//배열에 추가
//			}
//		}
//		
//		for (Calendar calendar : cals) {
//			System.out.println("2019-03-"+calendar.get(Calendar.DATE));
//		}	
//	}
	
	@Test
	public void test_08_readMonthList() throws Exception{
		List<Calendar> selectList = new ArrayList<>();
		
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.YEAR, 2019);
		cal1.set(Calendar.MONTH, 2-1);
		cal1.set(Calendar.DATE, 1);
		
		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.YEAR, 2019);
		cal2.set(Calendar.MONTH, 2-1);
		cal2.set(Calendar.DATE, 2);
		
		Calendar cal3 = Calendar.getInstance();
		cal3.set(Calendar.YEAR, 2019);
		cal3.set(Calendar.MONTH, 2-1);
		cal3.set(Calendar.DATE, 3);
		
		selectList.add(cal1);
		selectList.add(cal2);
		selectList.add(cal3);
		
		Calendar destCal = Calendar.getInstance();
		destCal.set(Calendar.YEAR, 2019);
		destCal.set(Calendar.MONTH, 4-1);

		List<Calendar> cals = new ArrayList<>();
		
		System.out.println("Max: "+destCal.getActualMaximum(Calendar.DATE));
		for(int i=1; i<=destCal.getActualMaximum(Calendar.DATE); i++) {//destMonth 1~31일
			destCal.set(Calendar.DATE, i);
			System.out.println(destCal.get(Calendar.DATE));

			for (Calendar calendar : selectList) {
				if(destCal.get(Calendar.WEEK_OF_MONTH)==calendar.get(Calendar.WEEK_OF_MONTH)
						&& destCal.get(Calendar.DAY_OF_WEEK)==calendar.get(Calendar.DAY_OF_WEEK)) { 
					//주, 요일이 같은 날
					Calendar addCal = Calendar.getInstance();
					addCal.set(Calendar.YEAR, 2019);
					addCal.set(Calendar.MONTH, 3-1);
					addCal.set(Calendar.DATE, i);
					System.out.println("add : "+addCal.get(Calendar.DATE));
					cals.add(addCal);//배열에 추가
				}
			}
		}
		
		for (Calendar calendar : cals) {
			System.out.println("2019-03-"+calendar.get(Calendar.DATE));
		}	
	}
}
