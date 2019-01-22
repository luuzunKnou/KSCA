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
	
	//로그인 페이지 이동
	@RequestMapping(value="/logIn")
	public String logInGet(HttpSession session){
		logger.info("LogIn Page..........");
		return "manager/logIn";
	}
	
	//로그인 : 메서드 실행 후 LoginInterceptor.postHandler에서 처리
	@RequestMapping(value="/logInPost", method=RequestMethod.POST)
	public void loginPost(Manager inputMember, Model model) throws Exception{
		Manager manager = service.readForLogin(
				inputMember.getId(), inputMember.getPassword());
		logger.info("Manager(Login Controller) :"+manager);
		if(manager==null){
			//interceptor에서 Manager키가 없으면 login화면으로 다시 가도록 처리
			return; 
		}
		//Manager 검색에 성공하면 attribute에 manager정보 저장
		model.addAttribute("manager", manager);
		return;
	}
	
	//회원가입 페이지 이동
	@RequestMapping(value="/signUp")
	public String signUpGET(HttpSession session){
		logger.info("SignUp Page..........");
		return "manager/signUp";
	}
	
	//회원가입 submit
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
	
	//로그아웃 처리
	@RequestMapping(value="/logOut")
	public String logout(HttpSession session){
		session.removeAttribute("id");
		session.removeAttribute("permission");
		session.invalidate();
		return "redirect:/";
	}
	
	//회원관리 창으로 이동
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
	
	//회원가입 승인 처리
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