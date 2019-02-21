package com.luuzun.ksca.controller;

import java.util.ArrayList;
import java.util.List;

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
			rttr.addFlashAttribute("msg","권한이 없습니다.");
			return "redirect:/";
		}
		String areaCode = manager.getArea();
		
		//Input Select를 위한 SCC, Program List
		List<SCC> sccList = sccService.readByAreaCode(areaCode);
		List<ProgramJoinForList> programList = programService.readProgramJoinForList(areaCode);
		model.addAttribute("sccList",sccList); //Used on Offer Modal
		model.addAttribute("programList",programList); //Used on Offer Program Modal
		
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
		 
		 if(offerProgram != null) { //중복시 0 반환
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
				//offerService.updateMonthlyOper(offerCode, scheduleList.size());//Offer에 monthly_oper(월 운영 횟수) 업데이트
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
		
		if(modeFlag.equals("0")) { //전체 수정
			if(dateStrList.length==1) { //주간반복이 변경되지 않았을 때
				if(scheduleService.checkDuplicate(offerCode, schedule)!=0) { //schedule.offer, date가 같은 값이 존재하면 생성하지 않음.
					scheduleService.delete(schedule.getCode());
				} else {
					scheduleService.updateByOffer(offerCode, schedule);
				}
			}
			
			if(dateStrList.length>1) { //주간반복이 변경되었을 때
				scheduleService.deleteByOffer(schedule.getOffer()); //where offerCode=value; //스케줄 전체삭제 후 재등록
				List<Schedule> scheduleList = dateStrToScheduleList(dateStrList, offerCode);
				scheduleService.createMany(scheduleList);
			}
		} else { //선택 날짜 수정
			schedule.setOffer(offerCode);
			if(scheduleService.checkDuplicate(offerCode, schedule)!=0) { //schedule.offer, date가 같은 값이 존재하면 생성하지 않음.
				scheduleService.delete(schedule.getCode());
			} else {
				scheduleService.update(schedule);
			}
		}
	}
	
	
	
	
	
	//offer가 존재하면 Offer Code return, 존재하지 않으면 생성
	private String checkOffer(Offer offer, String areaCode) throws Exception {
		String offerCode;

		//areaCode, branchCode, sccCode, program이 같으면 같은 offer임.
		Offer checkOffer = offerService.readForExistCheck(
				areaCode, offer.getBranchCode(), offer.getSccCode(), offer.getProgram());
		if( checkOffer != null) {
			offerCode = checkOffer.getCode(); //존재하는 offer code를 가져옴
		} else {
			offerCode = offerService.create(offer); //새로운 offer를 만듬
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
			
			if(scheduleService.checkDuplicate(offerCode, addSchedule)!=0) { //같은 schedule이 존재할 때
				
			} else {
				scheduleList.add(addSchedule);
			}
		}
		
		return scheduleList;
	}
}
