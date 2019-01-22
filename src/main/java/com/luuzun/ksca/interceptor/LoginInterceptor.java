package com.luuzun.ksca.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.luuzun.ksca.controller.ManagerController;
import com.luuzun.ksca.domain.Manager;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
		ModelAndView modelAndView) throws Exception {
		Manager manager = (Manager) modelAndView.getModel().get("manager");
		logger.info("Call postHandler:LoginInterceptor");
		logger.info("Manager(postHandler) :" + manager);

		if(manager==null){
			//response.sendRedirect("logIn"); //회원가입으로 이동
			return;
		} else { 
			//로그인시 session에 id를 넣음
			HttpSession session = request.getSession();
			session.setAttribute("id", manager.getId());
			session.setAttribute("permission", manager.permToString());
			
			//login 이전에 이동될 uri가 있다면 dest에 저장함
			//저장된 dest의 값을 받아 이동
			String path = (String)session.getAttribute("dest");
			logger.info("Dest Path : " + path);
			if(path != null){
				response.sendRedirect(path);
			} 
		}
	}
}
