package com.luuzun.ksca.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.luuzun.ksca.domain.Agency;
import com.luuzun.ksca.domain.Cat1;
import com.luuzun.ksca.domain.Cat2;
import com.luuzun.ksca.domain.Manager;
import com.luuzun.ksca.domain.Program;
import com.luuzun.ksca.domain.ProgramJoinForList;
import com.luuzun.ksca.service.AgencyService;
import com.luuzun.ksca.service.Cat1Service;
import com.luuzun.ksca.service.Cat2Service;
import com.luuzun.ksca.service.ProgramService;

@Controller
@RequestMapping("/program/*")
public class ProgramController {
	private static final Logger logger = LoggerFactory.getLogger(ProgramController.class);
	
	@Inject	private ProgramService service;
	@Inject	private Cat1Service cat1Service;
	@Inject	private Cat2Service cat2Service;
	@Inject	private AgencyService agencyService;
	
	//Program List
	@RequestMapping(value="/programList")
	public String programList(Model model, HttpSession session, RedirectAttributes rttr) throws Exception{
		logger.info("Program List Page..........");
		Manager manager = (Manager) session.getAttribute("login");

		//Permission Check
		if(manager==null) {
			rttr.addFlashAttribute("msg","권한이 없습니다.");
			return "redirect:/";
		}
		
		String areaCode = manager.getArea();
		
		//Get Program, Category, Agency List
		List<ProgramJoinForList> programList = service.readProgramJoinForList(areaCode);
		List<Cat1> catList = cat1Service.listAll();
		List<Agency> agencyList = agencyService.readByAreaCode(areaCode);
		
		model.addAttribute("programList",programList);
		model.addAttribute("catList",catList);
		model.addAttribute("agencyList",agencyList);
		return "program/programList";
	}
	
	//Create Program
	@ResponseBody
	@RequestMapping(value="/createProgram", method=RequestMethod.POST)
	public ProgramJoinForList createProgram(Program program, HttpSession session) throws Exception  {
		
		Manager manager=(Manager) session.getAttribute("login");
		String areaCode = manager.getArea();
		
		logger.info("Create Program.......... : " + program + " : " + areaCode);

		//Set insert Program
		program.setArea(areaCode);

		//Get Last Index
		String lastIdx = service.create(program);
		
		//Get Program Information
		ProgramJoinForList newProgram = service.readProgramJoinByCode(lastIdx);
		
		return newProgram;
	}
	
	
	
	//Modify Program
	@ResponseBody
	@RequestMapping(value="/modifyProgram", method=RequestMethod.POST)
	public ProgramJoinForList modifyProgram(Program program, Model model) throws Exception  {
		logger.info("Modify Program.......... : " + program);

		service.update(program);
		
		//Get Program Information
		ProgramJoinForList newProgram = service.readProgramJoinByCode(program.getCode());
		
		return newProgram;
	}
	
	
	//Get Category2 List
	@ResponseBody
	@RequestMapping(value="/getCat2List", method=RequestMethod.POST)
	public List<Cat2> getCat2List(String code) throws Exception  {
		logger.info("Get Category2 List..........");
		List<Cat2> cat2List = cat2Service.readByCat1(code);
		return cat2List;
	}
	
	
	//Remove Program
	@ResponseBody
	@RequestMapping(value="/removeProgram", method=RequestMethod.POST)
	public Program removeProgram(Model model, HttpServletRequest req, Program program) throws Exception {
		logger.info("Remove Program.......... : " + program);
		return service.delete(program.getCode());
	}
}
