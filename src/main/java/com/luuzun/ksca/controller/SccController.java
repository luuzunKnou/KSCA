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

import com.luuzun.ksca.domain.Branch;
import com.luuzun.ksca.domain.Manager;
import com.luuzun.ksca.domain.SCC;
import com.luuzun.ksca.service.BranchService;
import com.luuzun.ksca.service.SccService;

@Controller
@RequestMapping("/scc/*")
public class SccController {
	private static final Logger logger = LoggerFactory.getLogger(SccController.class);
	
	@Inject	private SccService service;
	@Inject	private BranchService branchService;
	
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
		
		String areaCode = manager.getArea();
		
		//Get SCC List
		List<SCC> sccList = service.readByAreaCode(areaCode);
		logger.info("SCC List : " + sccList.toString());
		model.addAttribute("sccList",sccList);

		//Get Area Code And BranchList
		List<Branch> branchList = branchService.readByAreaCode(areaCode);
		List<String> branchCodeList=new ArrayList<String>();
		List<String> branchNameList=new ArrayList<String>();
		
		//Get Branch Name List And BranchCode List
		for (Branch branch: branchList) {
			branchCodeList.add(branch.getBranchCode());
			branchNameList.add(branch.getBranch());
		}
		
		model.addAttribute("branchCodeList",branchCodeList);
		model.addAttribute("branchNameList",branchNameList);
		model.addAttribute("areaCode",areaCode);
		
		return "scc/sccList";
	}
	
	//Create SCC
	@ResponseBody
	@RequestMapping(value="/createScc", method=RequestMethod.POST)
	public SCC createScc(SCC scc, HttpSession session, Model model, String regDateStr) throws Exception  {
		logger.info("Create SCC..........");
		
		Manager manager=(Manager) session.getAttribute("login");
		String areaCode = manager.getArea();

		//Set insert SCC
		scc.setAreaCode(areaCode);
		if(regDateStr.length()!=0) {
			logger.info("setSimpleDate");	
			scc.setSimpleRegDate(regDateStr);
		}
		
		logger.info("SCC: "+scc);
		logger.info("regDateStr: "+regDateStr);
		
		//중복 입력 처리
		if(service.read(scc.getAreaCode(),scc.getBranchCode(), scc.getSccCode())
				!=null) {
			SCC errorScc = new SCC();
			errorScc.setSccCode("DUPLICATED");
			return errorScc;
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
	public SCC modifyScc(String destAreaCode, String destBranchCode, String destSccCode,
			SCC scc, Model model, String regDateStr) throws Exception  {
		
		logger.info("Modify SCC..........:"+regDateStr);
		
		//Set insert SCC
		if(regDateStr.length()!=0) {
			logger.info("setSimpleDate");	
			scc.setSimpleRegDate(regDateStr);
		}
		
		logger.info("SCC: "+scc);
		
		try {
			service.update(destAreaCode, destBranchCode, destSccCode, scc);
			model.addAttribute("updateScc",scc);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return scc;
	}
	
	
	
	//remove SCC
	@ResponseBody
	@RequestMapping(value="/removeScc", method=RequestMethod.POST)
	public SCC removeScc(Model model, HttpServletRequest req, SCC scc) throws Exception {
		logger.info("Remove SCC..........:"+scc);
		
		service.delete(scc.getAreaCode(), scc.getBranchCode(), scc.getSccCode());
		return scc;
	}
	
	
	//SCC Duplecation Check
	@ResponseBody
	@RequestMapping(value="/checkScc", method=RequestMethod.POST)
	public int checkScc(HttpServletRequest req, String areaCode, 
			String branchCode, String sccCode) throws Exception{
		
		logger.info("Check duplication Scc");
		 
		 SCC scc =  service.read(areaCode, branchCode, sccCode);
		 
		 if(scc != null) { //아이디 중복시 0 반환
			 return 0;
		 } 
		 return 1;
	}
}
