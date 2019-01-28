package com.luuzun.ksca.domain;

import java.util.List;

public class ManagerHasArea {
	private String id;
	private String password; 
	private String name;
	private String tel;
	private String mail;
	private boolean isApprove; 
	private boolean isExist;
	private Permission permission;
	private List<Area> areaList;
	
	@Override
	public String toString() {
		return String.format(
				"[id=%s, password=%s, name=%s, tel=%s, mail=%s, isApprove=%s, isExist=%s, permission=%s, areaList=%s]",
				id, password, name, tel, mail, isApprove, isExist, permission, areaList);
	}
	
	public Area getArea() {
		return this.areaList.get(0);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public boolean isApprove() {
		return isApprove;
	}
	public void setApprove(boolean isApprove) {
		this.isApprove = isApprove;
	}
	public boolean isExist() {
		return isExist;
	}
	public void setExist(boolean isExist) {
		this.isExist = isExist;
	}
	public Permission getPermission() {
		return permission;
	}
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	public List<Area> getAreaList() {
		return areaList;
	}
	public void setAreaList(List<Area> areaList) {
		this.areaList = areaList;
	}
}
