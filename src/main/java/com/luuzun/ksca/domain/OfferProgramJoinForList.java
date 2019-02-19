package com.luuzun.ksca.domain;

public class OfferProgramJoinForList {
	private Program program;
	private OfferProgram offerProgram;
	private Agency agency;
	
	@Override
	public String toString() {
		return String.format("OfferProgramJoinForList [program=%s, offerProgram=%s, agency=%s]", program, offerProgram,
				agency);
	}
	
	public Program getProgram() {
		return program;
	}
	public void setProgram(Program program) {
		this.program = program;
	}
	public OfferProgram getOfferProgram() {
		return offerProgram;
	}
	public void setOfferProgram(OfferProgram offerProgram) {
		this.offerProgram = offerProgram;
	}
	public Agency getAgency() {
		return agency;
	}
	public void setAgency(Agency agency) {
		this.agency = agency;
	}
}
