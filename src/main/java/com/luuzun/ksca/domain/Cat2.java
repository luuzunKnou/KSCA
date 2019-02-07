package com.luuzun.ksca.domain;

public class Cat2 {
	private String code; 
	private String name;
	private String cat1;
	
	@Override
	public String toString() {
		return String.format("Cat2 [code=%s, name=%s, cat1=%s]", code, name, cat1);
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
	public String getCat1() {
		return cat1;
	}
	public void setCat1(String cat1) {
		this.cat1 = cat1;
	}
}
