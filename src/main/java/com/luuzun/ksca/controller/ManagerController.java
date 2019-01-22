package com.luuzun.ksca.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.luuzun.ksca.domain.Manager;
import com.luuzun.ksca.service.ManagerService;

@Controller
@RequestMapping("/manager/*")
public class ManagerController {
	private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);
	
	@Inject
	private ManagerService service;
	
	//�α��� ������ �̵�
	@RequestMapping(value="/logIn")
	public String logInGet(HttpSession session){
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
			//interceptor���� ManagerŰ�� ������ loginȭ������ �ٽ� ������ ó��
			return; 
		}
		//Manager �˻��� �����ϸ� attribute�� manager���� ����
		model.addAttribute("manager", manager);
		return;
	}
	
	//ȸ������ ������ �̵�
	@RequestMapping(value="/signUp")
	public String signUpGET(HttpSession session){
		logger.info("SignUp Page..........");
		return "manager/signUp";
	}
	
	//ȸ������ submit
	@RequestMapping(value="/signUp", method=RequestMethod.POST)
	public String signUpPost(Manager manager, RedirectAttributes rttr){
		logger.info("SignUp..........");
		logger.info(manager.toString());
		
		try {
			service.create(manager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rttr.addFlashAttribute("msg","SignUp Success");
		return "redirect:/";
	}
	
	//�α׾ƿ� ó��
	@RequestMapping(value="/logOut")
	public String logout(HttpSession session){
		session.removeAttribute("id");
		session.removeAttribute("permission");
		session.invalidate();
		return "redirect:/";
	}
	
	//ȸ������ â���� �̵�
	@RequestMapping(value="/managerManagement")
	public String managerManagementGET(Model model, HttpSession session, RedirectAttributes rttr){
		logger.info("ReadWaitingManager..........");
		if(session.getAttribute("permission")!="Master") {
			logger.info("Permission Denined..........:" 
					+ session.getAttribute("permission"));
			rttr.addFlashAttribute("msg","Permission Denined");
			return "redirect:/";
		}
		try {
			logger.info("Get readWaitingManager..........");
			model.addAttribute("managers", service.readWaitingManager());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/managerManagement";
	}
	
	//ȸ������ ���� ó��
	@RequestMapping(value="/managerManagement", method=RequestMethod.POST)
	public String managerManagementPOST(String[] manager, RedirectAttributes rttr){
		if(manager==null){
			rttr.addFlashAttribute("msg","None data");
			return "redirect:/";
		}
		
		for (String id : manager) {
			try {
				logger.info(id);
				service.updateApproveManager(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		rttr.addFlashAttribute("msg","SignUp Approve");
		return "redirect:/";
	}
}