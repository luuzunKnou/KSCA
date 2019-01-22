
package com.luuzun.ksca.domain;

public class SCC {
	private String code;
	private String name;
	private Area area;
	private Manager manager;
	
	@Override
	public String toString() {
		return String.format("SCC [code=%s, name=%s, area=%s, manager=%s]", code, name, area, manager);
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
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
}
