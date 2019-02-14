package com.luuzun.ksca.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Offer {
	private String code; 
	private String areaCode; 
	private String branchCode;
	private String sccCode;
	private String program;
	private Date beginDate;
	private Date endDate;
	private int monthlyOper;
	private int activeUser;
	private String color;
	
	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	
	//Simple Begin Date
	public String getSimpleBeginDate() {
		String simpleBeginDate = "";
		if(this.beginDate!=null) {
			simpleBeginDate=sd.format(this.beginDate);
		}
		return simpleBeginDate;
	}
	
	public void setSimpleBeginDate(String strDate) {
		try {
			this.beginDate = sd.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	//Simple End Date
	public String getSimpleEndDate() {
		String simpleEndDate = "";
		if(this.endDate!=null) {
			simpleEndDate=sd.format(this.endDate);
		}
		return simpleEndDate;
	}
	
	public void setSimpleEndDate(String strDate) {
		try {
			this.endDate = sd.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return String.format(
				"Offer [code=%s, areaCode=%s, branchCode=%s, sccCode=%s, program=%s, beginDate=%s, endDate=%s, monthlyOper=%s, activeUser=%s, color=%s]",
				code, areaCode, branchCode, sccCode, program, beginDate, endDate, monthlyOper, activeUser, color);
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getSccCode() {
		return sccCode;
	}
	public void setSccCode(String sccCode) {
		this.sccCode = sccCode;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getMonthlyOper() {
		return monthlyOper;
	}
	public void setMonthlyOper(int monthlyOper) {
		this.monthlyOper = monthlyOper;
	}
	public int getActiveUser() {
		return activeUser;
	}
	public void setActiveUser(int activeUser) {
		this.activeUser = activeUser;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
