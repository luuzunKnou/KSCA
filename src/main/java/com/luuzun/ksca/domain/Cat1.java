package com.luuzun.ksca.domain;

public class Cat1 {
	private String code; 
	private String name;

	@Override
	public String toString() {
		return String.format("%ncode=%s, name=%s", code, name);
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
}
