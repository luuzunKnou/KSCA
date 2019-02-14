package com.luuzun.ksca.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Schedule {
	private String code;
	private String offer;
	private Date date;
	
	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	
	public String getSimpleDate() {
		String simpleDate = "";
		if(this.date!=null) {
			simpleDate=sd.format(this.date);
		}
		return simpleDate;
	}
	
	public void setSimpleDate(String strDate) {
		try {
			this.date = sd.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return String.format("Schedule [code=%s, offer=%s, date=%s]", code, offer, date);
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getOffer() {
		return offer;
	}
	public void setOffer(String offer) {
		this.offer = offer;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
