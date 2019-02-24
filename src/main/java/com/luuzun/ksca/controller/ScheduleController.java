package com.luuzun.ksca.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.luuzun.ksca.domain.Manager;
import com.luuzun.ksca.domain.Offer;
import com.luuzun.ksca.domain.OfferProgram;
import com.luuzun.ksca.domain.OfferProgramJoinForList;
import com.luuzun.ksca.domain.ProgramJoinForList;
import com.luuzun.ksca.domain.SCC;
import com.luuzun.ksca.domain.Schedule;
import com.luuzun.ksca.domain.ScheduleJoinforList;
import com.luuzun.ksca.service.OfferProgramService;
import com.luuzun.ksca.service.OfferService;
import com.luuzun.ksca.service.ProgramService;
import com.luuzun.ksca.service.SccService;
import com.luuzun.ksca.service.ScheduleService;

@Controller
@RequestMapping("/schedule/*")
public class ScheduleController {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);
	
	@Inject	private ScheduleService scheduleService;
	@Inject	private OfferService offerService;
	@Inject	private SccService sccService;
	@Inject	private ProgramService programService;
	@Inject	private OfferProgramService offerProgramService;

	//Schduler Page
	@RequestMapping(value="/scheduler")
	public String programList(Model model, HttpSession session, RedirectAttributes rttr) throws Exception{
		logger.info("Scheduler Page..........");
		
		Manager manager = (Manager) session.getAttribute("login");
		//Permission Check
		if(manager==null) {
			rttr.addFlashAttribute("msg","������ �����ϴ�.");
			return "redirect:/";
		}
		String areaCode = manager.getArea();
		
		//Input Select�� ���� SCC, Program List
		List<SCC> sccList = sccService.readByAreaCode(areaCode);
		List<ProgramJoinForList> programList = programService.readProgramJoinForList(areaCode);

		List<Map<String,Object>> dateList = scheduleService.readMonthList(areaCode);
		List<String> monthList = new ArrayList<String>();
		List<String> yearList = new ArrayList<String>();
		
		for (Map<String, Object> map : dateList) {
			yearList.add(map.get("year").toString());
			monthList.add(map.get("month").toString());
		}
		
		model.addAttribute("sccList",sccList); //Used on Offer Modal
		model.addAttribute("programList",programList); //Used on Offer Program Modal
		model.addAttribute("yearList",yearList);
		model.addAttribute("monthList",monthList);
		
		return "schedule/scheduler";
	}
	
	
	
	
	
	
	//Get OfferProgram List
	@RequestMapping(value="/getOfferProgram")
	@ResponseBody
	public List<OfferProgramJoinForList> getOfferProgram(HttpSession session, String regMonth){
		logger.info("Get OfferProgram..........");

		Manager manager = (Manager) session.getAttribute("login");
		String areaCode = manager.getArea();
		
		List<OfferProgramJoinForList> offerProgramList 
				= offerProgramService.readOfferProgramJoinForList(
						areaCode, regMonth);
	
		return offerProgramList;
	}
	
	//Create Offer Program
	@ResponseBody
	@RequestMapping(value="/createOfferProgram", method=RequestMethod.POST)
	public ResponseEntity<String> createOfferProgram(OfferProgram offerProgram, 
			String regMonthStr, String beginDateStr, String endDateStr) {

		logger.info("Create Offer Program..........");
		
		ResponseEntity<String> entity = null;
		
		//DateType Attribute
		offerProgram.setSimpleRegMonth(regMonthStr);
		offerProgram.setSimpleBeginDate(beginDateStr);
		offerProgram.setSimpleEndDate(endDateStr);
		
		try {
			offerProgramService.create(offerProgram);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;
	}
	
	
	//Delete Offer Program
	@ResponseBody
	@RequestMapping(value="/deleteOfferProgram", method=RequestMethod.POST)
	public ResponseEntity<String> deleteOfferProgram(String offerProgramCode) {

		logger.info("Delete Offer Program..........");
		ResponseEntity<String> entity = null;
		
		try {
			offerProgramService.delete(offerProgramCode);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;
	}
	
	
	//Modify Offer Program
	@ResponseBody
	@RequestMapping(value="/modifyOfferProgram", method=RequestMethod.POST)
	public ResponseEntity<String> modifyOfferProgram(OfferProgram offerProgram, 
			String regMonthStr, String beginDateStr, String endDateStr) {

		logger.info("Modify Offer Program..........");
		
		ResponseEntity<String> entity = null;
		
		//DateType Attribute
		offerProgram.setSimpleRegMonth(regMonthStr);
		offerProgram.setSimpleBeginDate(beginDateStr);
		offerProgram.setSimpleEndDate(endDateStr);
		
		try {
			offerProgramService.update(offerProgram);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;
	}
		
	//Check OfferProgram
	@ResponseBody
	@RequestMapping(value="/checkOfferProgram", method=RequestMethod.POST)
	public int checkOfferProgram(HttpServletRequest req, String program, String regMonthStr) throws Exception{
		logger.info("Check Duplication OfferProgram");
		 
		 OfferProgram offerProgram 
		 	= offerProgramService.readForCheck(program, regMonthStr);
		 
		 if(offerProgram != null) { //�ߺ��� 0 ��ȯ
			 return 0;
		 } 
		 return 1;
	}
	
	
	
	
	
	
	
	
	
	//Get Schedule List
	@RequestMapping(value="/getSchedule")
	@ResponseBody
	public List<ScheduleJoinforList> getSchedule(HttpSession session, String thisMonth, String thisYear){
		logger.info("Get Schedule..........");

		Manager manager = (Manager) session.getAttribute("login");
		String areaCode = manager.getArea();
		
		List<ScheduleJoinforList> scheduleList = scheduleService.scheduleJoinforList(areaCode, thisMonth, thisYear);
	
		return scheduleList;
	}
	
	//Create Schedule
	@ResponseBody
	@RequestMapping(value="/createSchedule", method=RequestMethod.POST)
	public ResponseEntity<String> createSchedule(HttpSession session, Offer offer, String[] dateStrList) {

		logger.info("Create Schedule..........");
		
		ResponseEntity<String> entity = null;
		
		Manager manager=(Manager) session.getAttribute("login"); 
		String areaCode = manager.getArea();
		
		//Set AreaCode And DateType Attribute
		offer.setAreaCode(areaCode);

		
		try {
			String offerCode = checkOffer(offer, areaCode);
			
			List<Schedule> scheduleList = dateStrToScheduleList(dateStrList, offerCode);
			if(scheduleList.size()!=0) {
				
				scheduleService.createMany(scheduleList);
				//offerService.updateMonthlyOper(offerCode, scheduleList.size());//Offer�� monthly_oper(�� � Ƚ��) ������Ʈ
			}
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}

		return entity;
	}
	
	//Modify Schedule
	@ResponseBody
	@RequestMapping(value="/modifySchedule", method=RequestMethod.POST)
	public void modifySchedule(HttpSession session, Offer offer, Schedule schedule, String schCode, String dateStr, 
			String modeFlag, String[] dateStrList) throws Exception {
		
		Manager manager=(Manager) session.getAttribute("login"); 
		String areaCode = manager.getArea();
		
		offer.setAreaCode(areaCode);
		schedule.setCode(schCode);
		schedule.setSimpleDate(dateStr);
		
		logger.info("Modify Schedule..........");
		
		String offerCode = checkOffer(offer, areaCode);
		
		if(modeFlag.equals("0")) { //��ü ����
			if(dateStrList.length==1) { //�ְ��ݺ��� ������� �ʾ��� ��
				if(scheduleService.checkDuplicate(offerCode, schedule)!=0) { //schedule.offer, date�� ���� ���� �����ϸ� �������� ����.
					scheduleService.delete(schedule.getCode());
				} else {
					scheduleService.updateByOffer(offerCode, schedule);
				}
			}
			
			if(dateStrList.length>1) { //�ְ��ݺ��� ����Ǿ��� ��
				scheduleService.deleteByOffer(schedule.getOffer()); //where offerCode=value; //������ ��ü���� �� ����
				List<Schedule> scheduleList = dateStrToScheduleList(dateStrList, offerCode);
				scheduleService.createMany(scheduleList);
			}
		} else { //���� ��¥ ����
			schedule.setOffer(offerCode);
			if(scheduleService.checkDuplicate(offerCode, schedule)!=0) { //schedule.offer, date�� ���� ���� �����ϸ� �������� ����.
				scheduleService.delete(schedule.getCode());
			} else {
				scheduleService.update(schedule);
			}
		}
	}
	
	
	//Modify Schedule
	@ResponseBody
	@RequestMapping(value="/deleteSchedule", method=RequestMethod.POST)
	public ResponseEntity<String> deleteSchedule(String schCode, String offerCode, String modeFlag) throws Exception {
		logger.info("Delete Schedule..........");
		
		ResponseEntity<String> entity = null;
		
		try {
			if(modeFlag.equals("0")) {
				scheduleService.deleteByOffer(offerCode);
			} else {
				scheduleService.delete(schCode);
			}
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}

		return entity;
	}
	
	
	//offer�� �����ϸ� Offer Code return, �������� ������ ����
	private String checkOffer(Offer offer, String areaCode) throws Exception {
		String offerCode;

		//areaCode, branchCode, sccCode, offerProgram�� ������ ���� offer��.
		Offer checkOffer = offerService.readForExistCheck(
				areaCode, offer.getBranchCode(), offer.getSccCode(), offer.getProgram());
		if( checkOffer != null) {
			offerCode = checkOffer.getCode(); //�����ϴ� offer code�� ������
		} else {
			offerCode = offerService.create(offer).getCode(); //���ο� offer�� ����
		}
		return offerCode;
	}
	
	//dateStrList To scheduleList
	private List<Schedule> dateStrToScheduleList(String[] dateStrList, String offerCode){
		List<Schedule> scheduleList = new ArrayList<>();
		
		for (String date: dateStrList) { //Schedule List Insert
			Schedule addSchedule = new Schedule();		
			addSchedule.setSimpleDate(date);
			addSchedule.setOffer(offerCode);
			
			if(scheduleService.checkDuplicate(offerCode, addSchedule)!=0) { //���� schedule�� ������ ��
				
			} else {
				scheduleList.add(addSchedule);
			}
		}
		
		return scheduleList;
	}
	
	
	
	
	//loadSchedule
	@ResponseBody
	@RequestMapping(value="/loadSchedule", method=RequestMethod.POST)
	public ResponseEntity<String> loadSchedule(HttpSession session, String srcMonth, String destMonth) throws Exception {
		logger.info("Load Schedule..........: "+ srcMonth +" >>>> "+ destMonth);
		ResponseEntity<String> entity = null;
		
		Manager manager=(Manager) session.getAttribute("login");
		String areaCode = manager.getArea();
		
		String insertCode;
		String srcCode;
		

		try {
			List<OfferProgram> offerProgramList = offerProgramService.readByRegMonth(areaCode, srcMonth);
			Map<String, String> offerProgramCodeList = new HashMap<String, String>();
			Calendar defEndDate = Calendar.getInstance();
			
			//offerProgram RegMonth Change
			for (OfferProgram offerProgram : offerProgramList) {
				offerProgram.setSimpleRegMonth(destMonth);
				
				defEndDate.setTime(offerProgram.getRegMonth());
				offerProgram.setSimpleBeginDate(destMonth);
				offerProgram.setSimpleEndDate(""+defEndDate.get(Calendar.YEAR)+"-"+(defEndDate.get(Calendar.MONTH)+1)+"-"+defEndDate.getActualMaximum(Calendar.DATE));
			}
			
			//inert offerProgram
			OfferProgram offerProgramDupCheck;
			for (OfferProgram offerProgram : offerProgramList) {
				offerProgramDupCheck //�ߺ� üũ 
					= offerProgramService.readForCheck(offerProgram.getProgram(), offerProgram.getSimpleRegMonth());
				if(offerProgramDupCheck != null) { //offer program�� �����ϸ� code�� ������
					insertCode = offerProgramDupCheck.getCode(); 
				} else { //offer program�� �������� ������ ����
					insertCode = offerProgramService.create(offerProgram).getCode();
				}
				
				srcCode	   = offerProgram.getCode();
				offerProgramCodeList.put(srcCode, insertCode);
			}

		/***************************************************************************/		
			
			List<Offer> offerList = offerService.readByRegMonth(areaCode, srcMonth);
			Map<String, String> offerCodeList = new HashMap<String, String>();
			//offerList program Change
			for (Offer offer: offerList) {
				for(String key : offerProgramCodeList.keySet()){
		            String value = offerProgramCodeList.get(key);
		            if(offer.getProgram().equals(key)) { //key�� ������ value�� ����
		            	offer.setProgram(value);
		            }//////////////////////////////////ù ��°�� offer�� ������������... �ι�°�� ������. ��?
		        }
			}
			
			//inert offer
			for (Offer offer: offerList) {
				srcCode	   = offer.getCode();
				insertCode = checkOffer(offer, offer.getAreaCode()); 
				offerCodeList.put(srcCode, insertCode);
			}

		/***************************************************************************/
			OfferProgram offerProgram = offerProgramList.get(0);
			
			List<Schedule> scheduleList = scheduleService.readByRegMonth(areaCode, srcMonth);
			Date changeDate = new Date();
			//Schedule Code Change
			for (Schedule schedule: scheduleList) {
				for(String key : offerCodeList.keySet()){
		            String value = offerCodeList.get(key);
		            if(schedule.getOffer().equals(key)) { //key�� ������ value�� ����
		            	schedule.setOffer(value);
		            }
		        }
				changeDate = changeDate(schedule.getDate(),offerProgram.getRegMonth());
				if(changeDate!=null) {
					schedule.setDate(changeDate);//Date ����
				}
			}
			
			//inert schedule
			for (Schedule schedule: scheduleList) {
				if(scheduleService.checkDuplicate(schedule.getOffer(), schedule)!=0) { //���� schedule�� ������ ��
					
				} else {
					scheduleService.create(schedule);
				}
			}
			
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}

		return entity;
	}
	
	//srcDate�� destDate�� ��/��¥�� �°� ����
	private Date changeDate(Date srcDate, Date destMonth) { 
		Calendar srcCal = new GregorianCalendar();
		srcCal.setTime(srcDate);

		Calendar destCal = new GregorianCalendar();
		destCal.setTime(destMonth);

		for(int i=1; i<=destCal.getActualMaximum(Calendar.DATE); i++) {//destMonth 1~31�� //System.out.println(destCal.get(Calendar.YEAR)+"-"+destCal.get(Calendar.MONTH)+"-"+destCal.get(Calendar.DATE));
			destCal.set(Calendar.DATE, i);
			if(destCal.get(Calendar.WEEK_OF_MONTH)==srcCal.get(Calendar.WEEK_OF_MONTH) 			
					&& destCal.get(Calendar.DAY_OF_WEEK)==srcCal.get(Calendar.DAY_OF_WEEK)) { 
			//��, ������ ���� ��
			return new Date(destCal.getTimeInMillis());
			}
		}
		
		return null;
	}
}
