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
import com.luuzun.ksca.service.ManagerService;

@Controller
@RequestMapping("/manager/*")
public class ManagerController {
	private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);
	
	@Inject	private ManagerService service;
	@Inject	private AreaService areaService;
	
	//�α��� ������ �̵�
	@RequestMapping(value="/logIn")
	public String logInGet(Model model){
		logger.info("LogIn Page..........");
		return "manager/logIn";
	}
	
	//�α��� : �޼��� ���� �� LoginInterceptor.postHandler���� ó��
	@RequestMapping(value="/logInPost", method=RequestMethod.POST)
	public void loginPost(Manager inputMember, Model model) throws Exception{
		Manager manager = service.readForLogin(
				inputMember.getId(), inputMember.getPassword());
		logger.info("Manager(Login Controller) :"+manager);
		if(manager==null){
			//interceptor���� Manager ������ ������ loginȭ������ return
			logger.info("Cannot Find Manager");
			model.addAttribute("manager",null);
			return;
		}
		//Manager �˻��� �����ϸ� attribute�� manager���� ����
		logger.info("Set manager Attribute");
		model.addAttribute("manager", manager);
		return;
	}
	
	//�α׾ƿ� ó��
	@RequestMapping(value="/logOut")
	public String logout(HttpSession session){
		session.removeAttribute("login");
		session.invalidate();
		return "redirect:/";
	}
	
	
	
	
	//ȸ������ ������ �̵�
	@RequestMapping(value="/signUp")
	public String signUpGET(Model model){
		logger.info("SignUp Page..........");
		return "manager/signUp";
	}
	
	//ȸ������ submit
	@RequestMapping(value="/signUp", method=RequestMethod.POST)
	public String signUpPost(Manager manager, Area area, RedirectAttributes rttr) throws Exception{
		logger.info("SignUp..........");
		logger.info(manager.toString());
		
		//set area code
		area.setManager(manager.getId());
		area.setBranch("����");
		area.setBranchCode("99");
		area.SetCode(area.getCityCode(), area.getGuCode(), area.getBranchCode());
		logger.info(area.toString());
		
		//Transaction
		service.create(manager);
		areaService.create(area);

		rttr.addFlashAttribute("msg","ȸ�� ���� ��û�� �Ϸ�Ǿ����ϴ�.");
		return "redirect:/";
	}
	
	//���̵� �ߺ� üũ
	@ResponseBody
	@RequestMapping(value="/checkID", method=RequestMethod.POST)
	public int checkID(HttpServletRequest req) throws Exception{
		logger.info("Check duplication ID");
		 
		 String id = req.getParameter("id");
		 Manager manager =  service.read(id);
		
		 logger.info(id+" : "+manager);
		 
		 if(manager != null) { //���̵� �ߺ��� 0 ��ȯ
			 return 0;
		 } 
		 return 1;
	}
	
	//���̵� �ߺ� üũ
	@ResponseBody
	@RequestMapping(value="/checkCode", method=RequestMethod.POST)
	public int checkCode(HttpServletRequest req) throws Exception{
		logger.info("Check duplication ID");
		 
		 String cityCode = req.getParameter("cityCode");
		 String guCode = req.getParameter("guCode");
		
		 StringBuffer sb = new StringBuffer();
			sb.append(cityCode);	
			sb.append("-");
			sb.append(guCode);
			sb.append("-99");
		String code = sb.toString();
		logger.info(code);
		
		Area area = areaService.read(code);
		 
		if(area != null) { //���̵� �ߺ��� 0 ��ȯ
			return 0;
		} 
		return 1;
	}
	
	
	
	
	//ȸ������ ���� ������ �̵�
	@RequestMapping(value="/modify")
	public String modifyGet(HttpSession session, Model model, RedirectAttributes rttr) throws Exception{
		logger.info("Modify Profile..........");
		Manager manager = (Manager) session.getAttribute("login");
		if(manager==null) {
			rttr.addFlashAttribute("msg","������ �����ϴ�.");
			return "redirect:/";
		}
		
		String id = manager.getId();
		logger.info(id);
		model.addAttribute("managerInfo",service.readManagerHasArea(id));
		return "manager/modify"; 
	}
	
	//ȸ������ ���� Post
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyPost(Manager manager, Area area, 
			HttpSession session, Model model, RedirectAttributes rttr) throws Exception{
		logger.info("Modify Profile Post..........");
		logger.info(manager.toString() + area.toString());

		//password null ó��
		if(manager.getPassword().length()==0) {
			manager.setPassword(null);
			logger.info("Set Password null..........");
		}
		
		//transaction
		service.update(manager);
		areaService.update(area);

		rttr.addFlashAttribute("msg","ȸ�� ���� ������ �Ϸ�Ǿ����ϴ�.");
		return "redirect:/";
	}
	
	//ȸ�� Ż�� Post
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String removePost(String id, 
			HttpSession session, Model model, RedirectAttributes rttr) throws Exception{
		logger.info("Remove Profile Post.........."+id);
		
		service.leave(id);
		session.removeAttribute("login");
		session.invalidate();
		
		rttr.addFlashAttribute("msg","ȸ�� Ż�� �Ϸ�Ǿ����ϴ�.");
		return "redirect:/";
	}

	//ȸ������ â���� �̵�
	@RequestMapping(value="/managerManagement")
	public String managerManagementGET(Model model, HttpSession session, 
				RedirectAttributes rttr) throws Exception{
		logger.info("Read Waiting Manager..........");
		logger.info("Check Permision - "+session.getAttribute("login").toString());
		
		//Session�� ����� �Ŵ��� ������ ������
		Manager manager = (Manager) session.getAttribute("login");
		String permission = manager.permToString();
		
		//���� Ȯ��
		if(permission!="Master") {
			logger.info("Permission Denined..........:"	+ permission);
			rttr.addFlashAttribute("msg","������ �����ϴ�.");
			return "redirect:/";
		} else {
			logger.info("Get readWaitingManager..........");
			List<Manager> managers = service.readWaitingManager();
			if(managers.isEmpty()){
				rttr.addFlashAttribute("msg","���� ������� ȸ���� �����ϴ�.");
				return "redirect:/"; 
			}
			model.addAttribute("managers", managers);
		}
		return "manager/managerManagement";
	}
	
	//ȸ������ ���� ó��
	@RequestMapping(value="/managerManagement", method=RequestMethod.POST)
	public String managerManagementPOST(String[] manager, RedirectAttributes rttr) throws Exception{
		if(manager==null){
			rttr.addFlashAttribute("msg","������� ȸ���� �������� �ʽ��ϴ�.");
			return "redirect:/";
		}
		
		for (String id : manager) {
			logger.info(id);
			service.updateApproveManager(id);
		}
		rttr.addFlashAttribute("msg","ȸ�������� ���εǾ����ϴ�.");
		return "redirect:/";
	}
}

