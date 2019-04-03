package com.luuzun.ksca.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luuzun.ksca.domain.Agency;
import com.luuzun.ksca.domain.Area;
import com.luuzun.ksca.domain.Branch;
import com.luuzun.ksca.domain.Manager;
import com.luuzun.ksca.domain.ProgramJoinForList;
import com.luuzun.ksca.domain.SCC;
import com.luuzun.ksca.domain.ScheduleJoinforList;
import com.luuzun.ksca.service.AgencyService;
import com.luuzun.ksca.service.AreaService;
import com.luuzun.ksca.service.BranchService;
import com.luuzun.ksca.service.ManagerService;
import com.luuzun.ksca.service.ProgramService;
import com.luuzun.ksca.service.SccService;
import com.luuzun.ksca.service.ScheduleService;

@Controller
@RequestMapping("/android/*")
public class AndroidController {
	private static final Logger logger = LoggerFactory.getLogger(AndroidController.class);
	
	@Inject	private ManagerService managerService;
	@Inject	private AreaService areaService;
	@Inject	private BranchService branchService;
	@Inject	private AgencyService agencyService;
	@Inject	private ProgramService programService;
	@Inject	private SccService sccService;
	@Inject	private ScheduleService scheduleService;
	
	@RequestMapping("/login")
	@ResponseBody
	public Map<String,Object> login(HttpServletRequest request) throws Exception{ 
		logger.info("Call by Android - Log In");
		Map<String,Object> result = new HashMap<>();

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String areaName = null;
		
		Manager manager = null;
		manager = managerService.readForLogin(id, password); //ID, password 확인
		
		if(manager==null){
			result.put("ERROR", "FAIL");
		} else if(!manager.isApprove()) {
			result.put("ERROR", "NOT_APPROVE");
		} else if(!manager.isExist()){
			result.put("ERROR", "NOT_EXIST");
		} else {
			Area area = areaService.read(manager.getArea()); //지회 이름 read
			areaName = area.getCity() + " " + area.getGu() + "지회";
			
			result.put("id", manager.getId());
			result.put("area", manager.getArea());
			result.put("areaName", areaName);
			result.put("permission", manager.getPermission());
		}
		
		for (String key : result.keySet()) {
			String value = (String) result.get(key).toString();
			logger.info(key + " : " + value);
		}

		return result;
	}
	
	@RequestMapping("/branch")
	@ResponseBody
	public List<Map<String,Object>> getBranch(HttpServletRequest request) throws Exception { 
		
		logger.info("Call by Android - Branch");
		String areaCode = request.getParameter("areaCode");
		logger.info(areaCode);
		
		List<Branch> branchList = branchService.readByAreaCode(areaCode);
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		
        int index=0;
        for (Branch branch: branchList) {
        	Map<String,Object> map = new HashMap<String,Object>();
        	map.put("areaCode", branch.getAreaCode());
            map.put("branchCode", branch.getBranchCode());
            map.put("branch", branch.getBranch());
            result.add(index++,map);
		}
        
        for (Map<String, Object> map : result) {
        	 for(String key : map.keySet()){
                 String value = (String) map.get(key);
                 logger.info(key+" : "+value);
             }
		}
        
        return result;
	}
	
	
	@RequestMapping("/agency")
	@ResponseBody
	public List<Map<String,Object>> getAgency(HttpServletRequest request) throws Exception { 
		
		logger.info("Call by Android - Agency");
		String areaCode = request.getParameter("areaCode");
		logger.info(areaCode);
		
		List<Agency> agencyList = agencyService.readByAreaCode(areaCode);
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();

        int index=0;
        for (Agency agency: agencyList) {
        	Map<String,Object> map = new HashMap<String,Object>();
        	map.put("code", agency.getCode());
        	map.put("name", agency.getName());
            map.put("manager", agency.getManager());
            map.put("tel", agency.getTel());
            result.add(index++,map);
		}
        
        for (Map<String, Object> map : result) {
        	 for(String key : map.keySet()){
                 String value = (String) map.get(key);
                 logger.info(key+" : "+value);
             }
		}
        
        return result;
	}
	
	@RequestMapping("/program")
	@ResponseBody
	public List<Map<String,Object>> getProgram(HttpServletRequest request) throws Exception { 
		
		logger.info("Call by Android - Program");
		String areaCode = request.getParameter("areaCode");
		logger.info(areaCode);
		
		List<ProgramJoinForList> programList = programService.readProgramJoinForList(areaCode);
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();

        int index=0;
        for (ProgramJoinForList program: programList) {
        	Map<String,Object> map = new HashMap<String,Object>();
        	map.put("program", program.getProgram());
        	map.put("agency", program.getAgency());
            map.put("cat1", program.getCat1());
            map.put("cat2", program.getCat2());
            result.add(index++,map);
		}
        
        for (Map<String, Object> map : result) {
        	 for(String key : map.keySet()){
        		 Object value = (Object) map.get(key);
                 logger.info(key+" : "+value.toString());
             }
		}
        
        return result;
	}
	
	@RequestMapping("/scc")
	@ResponseBody
	public List<Map<String,Object>> getScc(HttpServletRequest request) throws Exception { 
		
		logger.info("Call by Android - SCC");
		String areaCode = request.getParameter("areaCode");
		logger.info(areaCode);
		
		List<SCC> sccList = sccService.readByAreaCode(areaCode);
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();

        int index=0;
        for (SCC scc: sccList) {
        	Map<String,Object> map = new HashMap<String,Object>();
        	
        	map.put("areaCode", scc.getAreaCode());
        	map.put("branchCode", scc.getBranchCode());
        	map.put("sccCode", scc.getSccCode());
        	map.put("dong", scc.getDong());
        	map.put("name", scc.getName());
        	map.put("address", scc.getAddress());
        	map.put("simpleRegDate", scc.getSimpleRegDate());
        	map.put("site", scc.getSite());
        	map.put("building", scc.getBuilding());
        	map.put("member", scc.getMember());
        	map.put("male", scc.getMale());
        	map.put("female", scc.getFemale());
        	map.put("own", scc.getOwn());
        	map.put("tel", scc.getTel());
        	map.put("president", scc.getPresident());
        	map.put("phone", scc.getPhone());
	
            result.add(index++,map);
		}
        
        for (Map<String, Object> map : result) {
        	 for(String key : map.keySet()){
        		 Object value = (Object) map.get(key);
        		 if(value!=null) {
        			 logger.info(key+" : "+value.toString());
        		 }
             }
		}
        
        return result;
	}
	
	@RequestMapping("/schedule")
	@ResponseBody
	public List<Map<String,Object>> getSchedule(HttpServletRequest request) throws Exception { 
		
		logger.info("Call by Android - Schedule");
		String areaCode = request.getParameter("areaCode");
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		logger.info(areaCode+"-"+year+"-"+month);
		
		List<ScheduleJoinforList> scheduleList = scheduleService.scheduleJoinforList(areaCode, month, year);
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();

        int index=0;
        for (ScheduleJoinforList schedule: scheduleList) {
        	Map<String,Object> map = new HashMap<String,Object>();
        	
        	map.put("offer", schedule.getOffer());
        	map.put("scc", schedule.getScc());
        	map.put("schedule", schedule.getSchedule());
        	map.put("offerProgram", schedule.getOfferProgram());
        	map.put("program", schedule.getProgram());
	
            result.add(index++,map);
		}
        
        for (Map<String, Object> map : result) {
        	 for(String key : map.keySet()){
        		 Object value = (Object) map.get(key);
        		 if(value!=null) {
        			 logger.info(key+" : "+value.toString());
        		 }
             }
		}
        
        return result;
	}
}
