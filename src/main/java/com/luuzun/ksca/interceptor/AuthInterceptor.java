package com.luuzun.ksca.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.luuzun.ksca.controller.ManagerController;
import com.luuzun.ksca.utill.SaveDestUtill;

//기능 실행 시 권한을 확인.
public class AuthInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String permission = (String) session.getAttribute("permission");

		logger.info("Call preHandle:AuthInterceptor");
		logger.info("Session userID : "+ id);
		logger.info("Session Permission : "+ permission);
		 
		//아이디가 null이면 로그인 페이지로 이동
		if(id == null){
			//로그인 화면으로 이동하기 전 주소 기억
			SaveDestUtill.getInstance().saveDest(request); 
			String url = request.getContextPath();
			response.sendRedirect(url + "/manager/logIn");
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}
}