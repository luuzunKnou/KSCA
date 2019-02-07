
package com.luuzun.ksca.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SCC {
	private String areaCode; 
	private String branchCode;
	private String sccCode;
	private String dong; 
	private String name; 
	private String address; 
	private Date regDate; 
	private float site; 
	private float building; 
	private int member; 
	private int male; 
	private int female; 
	private String own; 
	private String tel;
	private String president; 
	private String phone ;
	
	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	
	public String getSimpleRegDate() {
		String simpleRegDate = "";
		if(this.regDate!=null) {
			simpleRegDate=sd.format(this.regDate);
		}
		return simpleRegDate;
	}
	
	public void setSimpleRegDate(String strDate) {
		try {
			this.regDate = sd.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return String.format(
				"SCC [areaCode=%s, branchCode=%s, sccCode=%s, dong=%s, name=%s, address=%s, regDate=%s, site=%s, building=%s, member=%s, male=%s, female=%s, own=%s, tel=%s, president=%s, phone=%s, sd=%s]",
				areaCode, branchCode, sccCode, dong, name, address, regDate, site, building, member, male, female, own,
				tel, president, phone, sd);
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getSccCode() {
		return sccCode;
	}

	public void setSccCode(String sccCode) {
		this.sccCode = sccCode;
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

	public SimpleDateFormat getSd() {
		return sd;
	}

	public void setSd(SimpleDateFormat sd) {
		this.sd = sd;
	}
}
