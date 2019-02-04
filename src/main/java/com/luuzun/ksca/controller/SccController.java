package com.luuzun.ksca.controller;

import java.util.ArrayList;
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

import com.luuzun.ksca.domain.Area;
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
		
		//Permission Check
		if(manager==null) {
			rttr.addFlashAttribute("msg","권한이 없습니다.");
			return "redirect:/"; 
		}
		
		//Get SCC List
		String managerID = manager.getId();
		List<SCC> sccList = service.readByManager(managerID);
		logger.info("SCC List : " + sccList.toString());

		model.addAttribute("sccList",sccList);

		//Get Front Code And BranchList
		List<Area> areaList = areaService.readByManager(managerID);
		
		String frontCode= areaList.get(0).getCode().substring(0,6);
		List<String> branchCodeList=new ArrayList<String>();
		List<String> branchNameList=new ArrayList<String>();
		
		//Get Branch Name List And BranchCode List
		for (Area area : areaList) {
			branchCodeList.add(area.getBranchCode());
			branchNameList.add(area.getBranch());
		}
		
		model.addAttribute("branchCodeList",branchCodeList);
		model.addAttribute("branchNameList",branchNameList);
		model.addAttribute("frontCode",frontCode);
		
		return "scc/sccList";
	}
	
	//Create SCC
	@ResponseBody
	@RequestMapping(value="/createScc", method=RequestMethod.POST)
	public SCC createScc(SCC scc, HttpSession session, Model model, String regDateStr) throws Exception  {
		logger.info("Create SCC..........");
		
		Manager manager=(Manager) session.getAttribute("login");
		
		//Set insert SCC
		scc.setManager(manager.getId());
		scc.setArea(scc.getCode().substring(0,8));
		if(regDateStr.length()!=0) {
			scc.setSimpleRegDate(regDateStr);
		}
		
		logger.info("SCC: "+scc);
		logger.info("regDateStr: "+regDateStr);
		//비어있는 값 처리
		
		//중복 입력 처리
		if(service.read(scc.getCode())!=null) {
			return new SCC("DUPLICATED");
		}
		
		try {
			service.create(scc);
			model.addAttribute("newScc",scc);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return scc;
	}
	
	
	
	//Modify SCC
	@ResponseBody
	@RequestMapping(value="/modifyScc", method=RequestMethod.POST)
	public SCC modifyScc(SCC scc, Model model, String regDateStr) throws Exception  {
		logger.info("Modify SCC..........");
		
		//Set insert SCC
		if(regDateStr.length()!=0) {
			scc.setSimpleRegDate(regDateStr);
		}
		
		logger.info("SCC: "+scc);
		
		try {
			service.update(scc);
			model.addAttribute("updateScc",scc);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return scc;
	}
	
	
	
	//remove SCC
	@ResponseBody
	@RequestMapping(value="/removeScc", method=RequestMethod.POST)
	public String removeScc(Model model, HttpServletRequest req, String code) throws Exception {
		logger.info("Remove SCC..........: " + code);
		
		service.delete(code);
		return code;
	}
}
