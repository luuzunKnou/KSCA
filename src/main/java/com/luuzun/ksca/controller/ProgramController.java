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
import com.luuzun.ksca.domain.Manager;
import com.luuzun.ksca.domain.Program;
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
		List<Program> programList = service.readByAreaCode(areaCode);
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
	public Program createProgram(Program program, HttpSession session) throws Exception  {
		logger.info("Create Program..........");
		
		Manager manager=(Manager) session.getAttribute("login");
		String areaCode = manager.getArea();

		//Set insert Program
		program.setArea(areaCode);
		int lastIdx = service.create(program);
		program.setCode(String.valueOf(lastIdx));

		return program;
	}
	
	
	
	//Modify Program
	@ResponseBody
	@RequestMapping(value="/modifyProgram", method=RequestMethod.POST)
	public Program modifyProgram(Program program, Model model) throws Exception  {
		logger.info("Modify Program..........:");
		service.update(program);
		return program;
	}
	
	
	
	//Remove Program
	@ResponseBody
	@RequestMapping(value="/removeProgram", method=RequestMethod.POST)
	public Program removeProgram(Model model, HttpServletRequest req, Program program) throws Exception {
		logger.info("Remove Program..........");
		
		service.delete(program.getCode());
		return program;
	}
}
