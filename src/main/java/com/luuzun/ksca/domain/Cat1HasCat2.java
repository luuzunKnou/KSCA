package com.luuzun.ksca.domain;

import java.util.List;

public class Cat1HasCat2 {
	private String code; 
	private String name;
	private List<Cat2> cat2List;
	@Override
	public String toString() {
		return String.format("%nCat1HasCat2 [code=%s, name=%s, cat2List=%s]", code, name, cat2List);
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
	
	public List<Cat2> getCat2List() {
		return cat2List;
	}
	
	public void setCat2List(List<Cat2> cat2List) {
		this.cat2List = cat2List;
	}
}
