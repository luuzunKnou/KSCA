package com.luuzun.ksca.utill;

import javax.servlet.http.HttpServletRequest;

public class SaveDestUtill {
	public static final SaveDestUtill instance = new SaveDestUtill();
	private SaveDestUtill() {}
	public static SaveDestUtill getInstance(){
		return instance;
	}
	
	//로그인 화면으로 이동하기 전 커맨드와 쿼리를 기억
	public void saveDest(HttpServletRequest request){
		String uri = request.getRequestURI();
		String query = request.getQueryString();
		
		if(query == null || query.equals("null")){
			query="";
		} else {
			query = "?" + query;
		}
		
		if(request.getMethod().equals("GET")){
			request.getSession().setAttribute("dest", uri + query);
		}
	}
}