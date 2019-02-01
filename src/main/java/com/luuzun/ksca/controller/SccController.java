package com.luuzun.ksca.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.luuzun.ksca.domain.Manager;
import com.luuzun.ksca.domain.SCC;
import com.luuzun.ksca.service.AreaService;
import com.luuzun.ksca.service.SccService;

@Controller
@RequestMapping("/scc/*")
public class SccController {
	private static final Logger logger = LoggerFactory.getLogger(SccController.class);
	
	@Inject	private SccService service;
	@Inject	private AreaService areaService;
	
	//SCC List
	@RequestMapping(value="/sccList")
	public String SccListGET(Model model, HttpSession session, RedirectAttributes rttr) throws Exception{
		logger.info("SCC List Page..........");
		Manager manager = (Manager) session.getAttribute("login");
		
		//권한 확인
		if(manager==null) {
			rttr.addFlashAttribute("msg","권한이 없습니다.");
			return "redirect:/"; 
		}
		
		//Get SCC List
		String managerID = manager.getId();
		List<SCC> sccList = service.readByManager(managerID);
		logger.info("SCC List : " + sccList.toString());

		model.addAttribute("sccList",sccList);

		//Get Front Code
		String frontCode=areaService.readFrontCode(managerID);
		model.addAttribute("frontCode",frontCode);
		
		return "scc/sccList";
	}
}
