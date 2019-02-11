package com.luuzun.ksca.domain;

public class ProgramJoinForList {
	private Program program;
	private Cat1 cat1;
	private Cat2 cat2;
	private Agency agency;
	
	@Override
	public String toString() {
		return String.format("ProgramJoinForList [program=%s, cat1=%s, cat2=%s, agency=%s]", program, cat1, cat2,
				agency);
	}
	
	public Program getProgram() {
		return program;
	}
	public void setProgram(Program program) {
		this.program = program;
	}
	public Cat1 getCat1() {
		return cat1;
	}
	public void setCat1(Cat1 cat1) {
		this.cat1 = cat1;
	}
	public Cat2 getCat2() {
		return cat2;
	}
	public void setCat2(Cat2 cat2) {
		this.cat2 = cat2;
	}
	public Agency getAgency() {
		return agency;
	}
	public void setAgency(Agency agency) {
		this.agency = agency;
	}
}
