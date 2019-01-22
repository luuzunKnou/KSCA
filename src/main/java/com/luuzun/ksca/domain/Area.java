package com.luuzun.ksca.domain;

public class Area {
	private String code; 
	private String manager;
	private String city;
	private String cityCode; 
	private String gu;
	private String guCode;
	private String branch;
	private String branchCode;
	
	public Area() {
		super();
	}

	public Area(String code, String manager, String city, String cityCode, String gu, String guCode, String branch,
			String branchCode) {
		super();
		this.code = code;
		this.manager = manager;
		this.city = city;
		this.cityCode = cityCode;
		this.gu = gu;
		this.guCode = guCode;
		this.branch = branch;
		this.branchCode = branchCode;
	}

	@Override
	public String toString() {
		return String.format(
				"Area [code=%s, manager=%s, city=%s, cityCode=%s, gu=%s, guCode=%s, branch=%s, branchCode=%s]", code,
				manager, city, cityCode, gu, guCode, branch, branchCode);
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getGu() {
		return gu;
	}
	public void setGu(String gu) {
		this.gu = gu;
	}
	public String getGuCode() {
		return guCode;
	}
	public void setGuCode(String guCode) {
		this.guCode = guCode;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
}
