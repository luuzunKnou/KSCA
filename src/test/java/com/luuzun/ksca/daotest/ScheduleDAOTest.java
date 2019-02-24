package com.luuzun.ksca.daotest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.luuzun.ksca.domain.OfferProgram;
import com.luuzun.ksca.domain.Schedule;
import com.luuzun.ksca.persistence.OfferDAO;
import com.luuzun.ksca.persistence.OfferProgramDAO;
import com.luuzun.ksca.persistence.ScheduleDAO;

@RunWith(SpringJUnit4ClassRunner.class) //Spring loading
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) //Spring loading
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScheduleDAOTest {
	@Inject private ScheduleDAO dao;
	@Inject	private OfferDAO offerDao;
	@Inject	private OfferProgramDAO offerProgramDao;
	
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
//	public void test_09_readMonthList() throws Exception{
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
//
//	@Test
//	public void test_10_readByRegMonth() throws Exception{
//		logger.info(dao.readByRegMonth("03-01","2019-02-01").toString());
//	}
	
	@Test
	public void test_12_loadTest() throws Exception{
		String areaCode = "03-01";
		String srcMonth = "2019-02-01";
		String destMonth = "2019-03-01";
		
		String insertCode;
		String srcCode;
		
		List<OfferProgram> offerProgramList = offerProgramDao.readByRegMonth(areaCode, srcMonth);
		Map<String, String> offerProgramCodeList = new HashMap<String, String>();
		//offerProgram RegMonth Change
		for (OfferProgram offerProgram : offerProgramList) {
			offerProgram.setSimpleRegMonth(destMonth);
		}
		
		//inert offerProgram
		for (OfferProgram offerProgram : offerProgramList) {
			srcCode	   = offerProgram.getCode();
			insertCode = offerProgramDao.create(offerProgram).getCode();
			offerProgramCodeList.put(srcCode, insertCode);
		}

	/***************************************************************************/		
		
		List<Offer> offerList = offerDao.readByRegMonth(areaCode, srcMonth);
		Map<String, String> offerCodeList = new HashMap<String, String>();
		//offerList program Change
		for (Offer offer: offerList) {
			for(String key : offerProgramCodeList.keySet()){
	            String value = offerProgramCodeList.get(key);
	            if(offer.getProgram().equals(key)) { //key와 같으면 value로 변경
	            	offer.setProgram(value);
	            }
	        }
		}
		
		//inert offer
		for (Offer offer: offerList) {
			srcCode	   = offer.getCode();
			insertCode = offerDao.create(offer).getCode();
			offerCodeList.put(srcCode, insertCode);
		}

	/***************************************************************************/
		OfferProgram offerProgram = offerProgramList.get(0);
		
		List<Schedule> scheduleList = dao.readByRegMonth(areaCode, srcMonth);
		Date changeDate = new Date();
		//Schedule Code Change
		for (Schedule schedule: scheduleList) {
			for(String key : offerCodeList.keySet()){
	            String value = offerCodeList.get(key);
	            if(schedule.getOffer().equals(key)) { //key와 같으면 value로 변경
	            	schedule.setOffer(value);
	            }
	        }
			changeDate = changeDate(schedule.getDate(),offerProgram.getRegMonth());
			if(changeDate!=null) {
				schedule.setDate(changeDate);//Date 변경
			}
		}

		
		//inert schedule
		for (Schedule schedule: scheduleList) {
			dao.create(schedule);
		}
	}
	
	private Date changeDate(Date srcDate, Date destMonth) {
		Calendar srcCal = new GregorianCalendar();
		srcCal.setTime(srcDate);

		Calendar destCal = new GregorianCalendar();
		destCal.setTime(destMonth);

		for(int i=1; i<=destCal.getActualMaximum(Calendar.DATE); i++) {//destMonth 1~31일 //System.out.println(destCal.get(Calendar.YEAR)+"-"+destCal.get(Calendar.MONTH)+"-"+destCal.get(Calendar.DATE));
			destCal.set(Calendar.DATE, i);
			if(destCal.get(Calendar.WEEK_OF_MONTH)==srcCal.get(Calendar.WEEK_OF_MONTH) 			
					&& destCal.get(Calendar.DAY_OF_WEEK)==srcCal.get(Calendar.DAY_OF_WEEK)) { 
			//주, 요일이 같은 날
			return new Date(destCal.getTimeInMillis());
			}
		}
		
		return null;
	}
	
//	@Test
//	public void test() throws Exception{
//		
//		Calendar cal = Calendar.getInstance();
//		cal.set(2019, 2-1, 30, 0, 0, 0);
//		cal.set(Calendar.MILLISECOND, 0);
//		System.out.println(cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.DATE));
//		
//		Date date = new Date();
//		SimpleDateFormat sd = new SimpleDateFormat("yyyy-mm-dd");
//		date = sd.parse("2019-02-30");
//		System.out.println(sd.format(date));
//	}
}
