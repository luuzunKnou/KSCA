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
import com.luuzun.ksca.domain.Manager;
import com.luuzun.ksca.service.AreaService;

@Controller
@RequestMapping("/area/*")
public class AreaController {
	private static final Logger logger = LoggerFactory.getLogger(AreaController.class);
	
	@Inject	private AreaService service;
	
	//Area List
	@RequestMapping(value="/areaList")
	public String areaListGET(Model model, HttpSession session, RedirectAttributes rttr) throws Exception{
		logger.info("Area List Page..........");
		Manager manager = (Manager) session.getAttribute("login");
		
		//���� Ȯ��
		if(manager==null) {
			rttr.addFlashAttribute("msg","������ �����ϴ�.");
			return "redirect:/"; 
		}
		
		String managerID = manager.getId();
		
		List<Area> areaList = service.readByManager(managerID);
		logger.info("Area List : " + areaList.toString());

		//BranchCode�� 99�� area �˻�
		int index=0;
		for (Area area : areaList) {
			if(area.getBranchCode().equals("99")){
				break;
			}
			index++;
		}
		
		Area defaultArea = areaList.get(index);
		logger.info("DefaultArea : " + defaultArea.toString());

		model.addAttribute("areaList",areaList);
		model.addAttribute("defaultArea",defaultArea);
		return "area/areaList";
	}
	
	//��ȸ �߰�
	@ResponseBody
	@RequestMapping(value="/createBranch", method=RequestMethod.POST)
	public Area createBranch(Area area, HttpSession session, Model model) throws Exception  {
		logger.info("Create Branch..........");
		//ResponseEntity<String> entity = null;
		
		Manager manager=(Manager) session.getAttribute("login");
		area.setManager(manager.getId());
		
		logger.info("area: "+area);
		
		//����ִ� �� ó��
		//�ߺ� �Է� ó��
		if(service.read(area.getCode())!=null) {
			return new Area("DUPLICATED");
		}
		
		try {
			service.create(area);
			//entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
			model.addAttribute("newArea",area);
		} catch (Exception e) {
			e.printStackTrace();
			//entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		//return entity;
		return area;
	}
	
	//��ȸ ����
	@ResponseBody
	@RequestMapping(value="/removeBranch", method=RequestMethod.POST)
	public String removeBranch(Model model, HttpServletRequest req, String code) throws Exception {
		logger.info("Remove Branch..........: " + code);
		
		//99 ���� �Ұ�
		String checkBranch = code.split("-")[2];
		if(checkBranch.equals("99")){
			return "ERROR";
		}
			
		service.delete(code);
		return code;
	}
}
