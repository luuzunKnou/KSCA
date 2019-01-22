package com.luuzun.ksca.domain;

public class Cat2 {
	private String code; 
	private String name;
	private Cat1 cat1;

	@Override
	public String toString() {
		return String.format("%ncode=%s, name=%s, cat1=%s", code, name, cat1);
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
	public Cat1 getParentsCategory() {
		return cat1;
	}
	public void setParentsCategory(Cat1 cat1) {
		this.cat1 = cat1;
	}
}
