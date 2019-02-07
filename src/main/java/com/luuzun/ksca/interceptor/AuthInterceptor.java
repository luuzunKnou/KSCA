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
import com.luuzun.ksca.utill.SaveDestUtill;

//��� ���� �� ������ Ȯ��.
public class AuthInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Manager manager = (Manager) session.getAttribute("login");

		logger.info("Call preHandle:AuthInterceptor");
		logger.info("Session userID : "+ manager);
		 
		//���̵� null�̸� �α��� �������� �̵�
		if(manager == null){
			//�α��� ȭ������ �̵��ϱ� �� �ּ� ���
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