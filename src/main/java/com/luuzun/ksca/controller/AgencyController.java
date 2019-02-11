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
import com.luuzun.ksca.domain.Manager;
import com.luuzun.ksca.service.AgencyService;

@Controller
@RequestMapping("/agency/*")
public class AgencyController {
	private static final Logger logger = LoggerFactory.getLogger(AgencyController.class);
	
	@Inject	private AgencyService service;
	
	//Agency List
	@RequestMapping(value="/agencyList")
	public String agencyList(Model model, HttpSession session, RedirectAttributes rttr) throws Exception{
		logger.info("Agency List Page..........");
		Manager manager = (Manager) session.getAttribute("login");

		//Permission Check
		if(manager==null) {
			rttr.addFlashAttribute("msg","권한이 없습니다.");
			return "redirect:/";
		}
		
		String areaCode = manager.getArea();
		
		//Get Agency List
		List<Agency> agencyList = service.readByAreaCode(areaCode);
		model.addAttribute("agencyList",agencyList);

		return "agency/agencyList";
	}
	
	//Create Agency
	@ResponseBody
	@RequestMapping(value="/createAgency", method=RequestMethod.POST)
	public Agency createAgency(Agency agency, HttpSession session) throws Exception  {
		logger.info("Create Agency..........");
		
		Manager manager=(Manager) session.getAttribute("login");
		String areaCode = manager.getArea();

		//Set insert Agency
		agency.setArea(areaCode);
		int lastIdx = service.create(agency);
		agency.setCode(String.valueOf(lastIdx));

		return agency;
	}
	
	
	
	//Modify Agency
	@ResponseBody
	@RequestMapping(value="/modifyAgency", method=RequestMethod.POST)
	public Agency modifyAgency(Agency agency, Model model) throws Exception  {
		logger.info("Modify Agency..........:");
		service.update(agency);
		return agency;
	}
	
	
	
	//Remove Agency
	@ResponseBody
	@RequestMapping(value="/removeAgency", method=RequestMethod.POST)
	public Agency removeAgency(Model model, HttpServletRequest req, Agency agency) throws Exception {
		logger.info("Remove SCC..........");
		
		service.delete(agency.getCode());
		return agency;
	}
}
