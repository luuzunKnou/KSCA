package com.luuzun.ksca.domain;

public class Branch {
	private String areaCode;
	private String branchCode;
	private String branch;
	
	@Override
	public String toString() {
		return String.format("Branch [areaCode=%s, branchCode=%s, branch=%s]", areaCode, branchCode, branch);
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
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
}
