package com.luuzun.ksca.domain;

public class Program {
	public String code;
	public String area;
	public String name;
	public String cat1;
	public String cat2;
	public String agency;
	
	@Override
	public String toString() {
		return String.format("Program [code=%s, area=%s, name=%s, cat1=%s, cat2=%s, agency=%s]", code, area, name, cat1,
				cat2, agency);
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
	public String getCat1() {
		return cat1;
	}
	public void setCat1(String cat1) {
		this.cat1 = cat1;
	}
	public String getCat2() {
		return cat2;
	}
	public void setCat2(String cat2) {
		this.cat2 = cat2;
	}
	public String getAgency() {
		return agency;
	}
	public void setAgency(String agency) {
		this.agency = agency;
	}
}
