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

import com.luuzun.ksca.domain.Area;
import com.luuzun.ksca.domain.Branch;
import com.luuzun.ksca.domain.Manager;
import com.luuzun.ksca.service.AreaService;
import com.luuzun.ksca.service.BranchService;
import com.luuzun.ksca.service.SccService;

@Controller
@RequestMapping("/branch/*")
public class BranchController {
	private static final Logger logger = LoggerFactory.getLogger(BranchController.class);
	
	@Inject	private BranchService service;
	@Inject	private AreaService areaService;
	@Inject	private SccService sccService;

	//Area List
	@RequestMapping(value="/branchList")
	public String branchListGET(Model model, HttpSession session, RedirectAttributes rttr) throws Exception{
		logger.info("Area List Page..........");
		Manager manager = (Manager) session.getAttribute("login");
		
		//권한 확인
		if(manager==null) {
			rttr.addFlashAttribute("msg","권한이 없습니다.");
			return "redirect:/"; 
		}
		
		String areaCode = manager.getArea();
				
		List<Branch> branchList = service.readByAreaCode(areaCode);
		logger.info("Branch List : " + branchList.toString());
		
		Area area = areaService.read(manager.getArea());
		
		model.addAttribute("area",area);
		model.addAttribute("branchList",branchList);
		return "branch/branchList";
	}
	
	//분회 추가
	@ResponseBody
	@RequestMapping(value="/createBranch", method=RequestMethod.POST)
	public Branch createBranch(Branch branch, HttpSession session, Model model) throws Exception  {
		logger.info("Create Branch..........");
		Manager manager = (Manager) session.getAttribute("login");
		branch.setAreaCode(manager.getArea());
		logger.info("Branch: "+branch);
		
		try {
			service.create(branch);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return branch;
	}
	
	
	//분회 수정
	@ResponseBody
	@RequestMapping(value="/updateBranch", method=RequestMethod.POST)
	public Branch updateBranch(Model model, HttpServletRequest req, HttpSession session,
			String destBranchCode, Branch branch) throws Exception {
		logger.info("Update Branch..........");
		Manager manager = (Manager) session.getAttribute("login");
		branch.setAreaCode(manager.getArea());
		logger.info(destBranchCode+":"+branch.toString());

		service.update(manager.getArea(), destBranchCode, branch);
		return branch;
	}
	
		
	//분회 삭제
	@ResponseBody
	@RequestMapping(value="/removeBranch", method=RequestMethod.POST)
	public String removeBranch(Model model, HttpServletRequest req, HttpSession session,
			String branchCode) throws Exception {
		Manager manager = (Manager) session.getAttribute("login");
		logger.info("Remove Branch..........: " + manager.getArea() + "-" + branchCode);

		//99 삭제 불가
		if(branchCode.equals("99")){
			return "ERROR:def";
		}
		
		//분회에 소속된 경로당이 존재하면 삭제 불가
		if(sccService.readByBranchCode(manager.getArea(), branchCode).size()!=0) {
			return "ERROR:cascade";
		}
		
		service.delete(manager.getArea(), branchCode);
		return branchCode;
	}
	
	
	//Branch Duplecation Check
	@ResponseBody
	@RequestMapping(value="/checkBranch", method=RequestMethod.POST)
	public int checkBranch(HttpServletRequest req, String areaCode, String branchCode) throws Exception{
		logger.info("Check duplication Branch");
		 
		 Branch branch =  service.read(areaCode, branchCode);
		 
		 if(branch != null) { //아이디 중복시 0 반환
			 return 0;
		 } 
		 return 1;
	}
}
