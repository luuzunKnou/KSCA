
package com.luuzun.ksca.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SCC {
	public String code; 
	public String dong; 
	public String name; 
	public String address; 
	public Date regDate; 
	public float site; 
	public float building; 
	public int member; 
	public int male; 
	public int female; 
	public String own; 
	public String tel;
	public String president; 
	public String phone ;
	public String manager; 
	public String area;
	
	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	
	public String getSimpleRegData() {
		String simpleRegDate = "";
		if(regDate!=null)
			simpleRegDate=sd.format(regDate);
		return simpleRegDate;
	}
	
	public void setSimpleRegData(String strDate) {
		try {
			this.regDate = sd.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public SCC() {
		super();
	}
	
	public SCC(String code) {
		super();
		this.code = code;
	}

	public SCC(String code, String name, String manager, String area) {
		super();
		this.code = code;
		this.name = name;
		this.manager = manager;
		this.area = area;
	}

	public SCC(String code, String dong, String name, String address, Date regDate, float site, float building,
			int member, int male, int female, String own, String tel, String president, String phone, String manager,
			String area) {
		super();
		this.code = code;
		this.dong = dong;
		this.name = name;
		this.address = address;
		this.regDate = regDate;
		this.site = site;
		this.building = building;
		this.member = member;
		this.male = male;
		this.female = female;
		this.own = own;
		this.tel = tel;
		this.president = president;
		this.phone = phone;
		this.manager = manager;
		this.area = area;
	}
	
	@Override
	public String toString() {
		return String.format(
				"SCC [code=%s, dong=%s, name=%s, address=%s, regDate=%s, site=%s, building=%s, member=%s, male=%s, female=%s, own=%s, tel=%s, president=%s, phone=%s, manager=%s, area=%s]",
				code, dong, name, address, regDate, site, building, member, male, female, own, tel, president, phone,
				manager, area);
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public float getSite() {
		return site;
	}
	public void setSite(float site) {
		this.site = site;
	}
	public float getBuilding() {
		return building;
	}
	public void setBuilding(float building) {
		this.building = building;
	}
	public int getMember() {
		return member;
	}
	public void setMember(int member) {
		this.member = member;
	}
	public int getMale() {
		return male;
	}
	public void setMale(int male) {
		this.male = male;
	}
	public int getFemale() {
		return female;
	}
	public void setFemale(int female) {
		this.female = female;
	}
	public String getOwn() {
		return own;
	}
	public void setOwn(String own) {
		this.own = own;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPresident() {
		return president;
	}
	public void setPresident(String president) {
		this.president = president;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
}
