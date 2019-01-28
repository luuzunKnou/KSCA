package com.luuzun.ksca.domain;

public class Category {
	private String code; 
	private String name;
	private String parCat;
	private String parName;
	
	@Override
	public String toString() {
		return String.format("%n[code=%s, name=%s, parCat=%s, parName=%s]", code, name, parCat, parName);
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParCat() {
		return parCat;
	}
	public void setParCat(String parCat) {
		this.parCat = parCat;
	}
	public String getParName() {
		return parName;
	}
	public void setParName(String parName) {
		this.parName = parName;
	}
}
