package com.luuzun.ksca.utill;

import javax.servlet.http.HttpServletRequest;

public class SaveDestUtill {
	public static final SaveDestUtill instance = new SaveDestUtill();
	private SaveDestUtill() {}
	public static SaveDestUtill getInstance(){
		return instance;
	}
	
	//�α��� ȭ������ �̵��ϱ� �� Ŀ�ǵ�� ������ ���
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