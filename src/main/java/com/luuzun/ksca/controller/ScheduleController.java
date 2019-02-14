package com.luuzun.ksca.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.luuzun.ksca.domain.Manager;
import com.luuzun.ksca.domain.ScheduleJoinforList;
import com.luuzun.ksca.service.OfferService;
import com.luuzun.ksca.service.ScheduleService;

@Controller
@RequestMapping("/schedule/*")
public class ScheduleController {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);
	
	@Inject	private ScheduleService scheduleService;
	@Inject	private OfferService offerService;

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
		
		return "schedule/scheduler";
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
}
