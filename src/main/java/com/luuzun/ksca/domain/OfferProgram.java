package com.luuzun.ksca.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OfferProgram {
	private String code; 
	private String program;
	private Date regMonth;
	private Date beginDate;
	private Date endDate;
	private String color;

	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	
	//Simple Reg Month
	public String getSimpleRegMonth() {
		String simpleRegMonth = "";
		if(this.regMonth!=null) {
			simpleRegMonth=sd.format(this.regMonth);
		}
		return simpleRegMonth;
	}
	
	public void setSimpleRegMonth(String regMonth) {
		try {
			this.regMonth = sd.parse(regMonth);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
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
				"OfferProgram [code=%s, program=%s, regMonth=%s, beginDate=%s, endDate=%s, color=%s, sd=%s]", code,
				program, regMonth, beginDate, endDate, color, sd);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public Date getRegMonth() {
		return regMonth;
	}

	public void setRegMonth(Date regMonth) {
		this.regMonth = regMonth;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public SimpleDateFormat getSd() {
		return sd;
	}

	public void setSd(SimpleDateFormat sd) {
		this.sd = sd;
	}
}
