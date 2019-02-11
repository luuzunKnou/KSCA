package com.luuzun.ksca.domain;

public class Agency {
	private String code;
	private String area;
	private String name;
	private String manager;
	private String tel;
	
	@Override
	public String toString() {
		return String.format("Agency [code=%s, area=%s, name=%s, manager=%s, tel=%s]", code, area, name, manager, tel);
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
}
