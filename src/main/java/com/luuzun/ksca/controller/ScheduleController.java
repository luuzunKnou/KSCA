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
			rttr.addFlashAttribute("msg","������ �����ϴ�.");
			return "redirect:/";
		}
		String areaCode = manager.getArea();
		
		//Input Select�� ���� SCC, Program List
		List<SCC> sccList = sccService.readByAreaCode(areaCode);
		List<ProgramJoinForList> programList = programService.readProgramJoinForList(areaCode);
		model.addAttribute("sccList",sccList);
		model.addAttribute("programList",programList);
		
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
	
	/*
	//Create Schedule
	@ResponseBody
	@RequestMapping(value="/createSchedule", method=RequestMethod.POST)
	public ResponseEntity<String> createSchedule(HttpSession session, Offer offer, 
			String regMonthStr, String beginDateStr, String endDateStr,	String[] dateStrList) {

		logger.info("Create Schedule..........");
		
		ResponseEntity<String> entity = null;
		
		Manager manager=(Manager) session.getAttribute("login"); 
		String areaCode = manager.getArea();
		
		//Set AreaCode And DateType Attribute
		offer.setAreaCode(areaCode);
		offer.setSimpleRegMonth(regMonthStr);
		offer.setSimpleBeginDate(beginDateStr);
		offer.setSimpleEndDate(endDateStr);
		
		try {
			String offerCode;
			Offer checkOffer;
			//areaCode, branchCode, sccCode, programm, regMonth�� ������ ���� offer��.
			checkOffer = offerService.readForExistCheck(
					areaCode, offer.getBranchCode(), offer.getSccCode(), 
					offer.getProgram(), offer.getSimpleRegMonth());
			if( checkOffer != null) {
				offerCode = checkOffer.getCode(); //�����ϴ� offer code�� ������
			} else {
				offerCode = offerService.create(offer); //���ο� offer�� ����
			}

			List<Schedule> scheduleList = new ArrayList<>();
			
			for (String date: dateStrList) { //Schedule List Insert
				Schedule addSchedule = new Schedule();		
				addSchedule.setSimpleDate(date);
				addSchedule.setOffer(offerCode);
				scheduleList.add(addSchedule);
			}
			logger.info("********: "+scheduleList);
			scheduleService.createMany(scheduleList);
			
			offerService.updateMonthlyOper(offerCode, scheduleList.size());//Offer�� monthly_oper(�� � Ƚ��) ������Ʈ
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;
	}
	
	//Set Color when program selected
	@ResponseBody
	@RequestMapping(value="/setColor", method=RequestMethod.POST)
	public String setColor(String regMonthStr, int programCode) {
		return offerService.readProgramColor(programCode, regMonthStr);
	}
	
	//Modify Schedule
	@ResponseBody
	@RequestMapping(value="/modifySchedule", method=RequestMethod.POST)
	public void modifySchedule(Offer offer, Schedule schedule, String schCode, String schDate, 
			String modeFlag) throws Exception {
	
		if(modeFlag=="0") { //��ü ����
			offerService.update(offer);
		} else { //���� ��¥ ����
			scheduleService.update(schedule);
		}
	}
	*/
}
