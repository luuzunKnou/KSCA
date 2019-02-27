package com.luuzun.ksca.daotest;

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
	
	@Test
	public void test_08_readMonthList() throws Exception{
		logger.info(dao.readMonthList("03-01").toString());
	}

	@Test
	public void test_10_readByRegMonth() throws Exception{
		logger.info(dao.readByRegMonth("03-01","2019-02-01").toString());
	}
	
	@Test
	public void test_11_loadTest() throws Exception{
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
	
	
	@Test
	public void test_13_excelOutput() throws Exception{
		logger.info(dao.excelOutput("03-01", "2019-02-01").toString());
	}
}
