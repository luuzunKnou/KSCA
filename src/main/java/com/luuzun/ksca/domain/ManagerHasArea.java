package com.luuzun.ksca.domain;

public class ManagerHasArea {
	private String id;
	private String password; 
	private String name;
	private String tel;
	private String mail;
	private boolean isApprove; 
	private boolean isExist;
	private Permission permission;
	private Area area;
	
	@Override
	public String toString() {
		return String.format(
				"ManagerHasArea [id=%s, password=%s, name=%s, tel=%s, mail=%s, isApprove=%s, isExist=%s, permission=%s, area=%s]",
				id, password, name, tel, mail, isApprove, isExist, permission, area);
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
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
}
