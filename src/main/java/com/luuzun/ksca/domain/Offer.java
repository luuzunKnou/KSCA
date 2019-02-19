package com.luuzun.ksca.domain;

public class Offer {
	private String code; 
	private String areaCode; 
	private String branchCode;
	private String sccCode;
	private String program;
	private int monthlyOper;
	private int activeUser;
	
	@Override
	public String toString() {
		return String.format(
				"Offer [code=%s, areaCode=%s, branchCode=%s, sccCode=%s, program=%s, monthlyOper=%s, activeUser=%s]",
				code, areaCode, branchCode, sccCode, program, monthlyOper, activeUser);
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
}
