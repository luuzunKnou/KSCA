package com.luuzun.ksca.domain;

public class Area {
	private String code; 
	private String city;
	private String cityCode; 
	private String gu;
	private String guCode;
	
	//Set code by this.**code
	public void setCode() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.cityCode);	
		sb.append("-");
		sb.append(this.guCode);	
		
		this.code=sb.toString();
	}
	
	//Set code by association other code
	public void setCode(String cityCode, String guCode) {
		StringBuffer sb = new StringBuffer();
		sb.append(cityCode);	
		sb.append("-");
		sb.append(guCode);		
		this.code=sb.toString();
	}

	@Override
	public String toString() {
		return String.format("Area [code=%s, city=%s, cityCode=%s, gu=%s, guCode=%s]", code, city, cityCode, gu,
				guCode);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getGu() {
		return gu;
	}

	public void setGu(String gu) {
		this.gu = gu;
	}

	public String getGuCode() {
		return guCode;
	}

	public void setGuCode(String guCode) {
		this.guCode = guCode;
	}
}