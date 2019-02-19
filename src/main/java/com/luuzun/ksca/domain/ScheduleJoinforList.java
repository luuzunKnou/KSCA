package com.luuzun.ksca.domain;

public class ScheduleJoinforList {
	private Offer offer;
	private SCC scc;
	private Schedule schedule;
	private OfferProgram offerProgram;
	private Program program;
	
	@Override
	public String toString() {
		return String.format("ScheduleJoinforList [offer=%s, scc=%s, schedule=%s, offerProgram=%s, program=%s]", offer,
				scc, schedule, offerProgram, program);
	}

	public Offer getOffer() {
		return offer;
	}
	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	public SCC getScc() {
		return scc;
	}
	public void setScc(SCC scc) {
		this.scc = scc;
	}
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	public OfferProgram getOfferProgram() {
		return offerProgram;
	}
	public void setOfferProgram(OfferProgram offerProgram) {
		this.offerProgram = offerProgram;
	}
	public Program getProgram() {
		return program;
	}
	public void setProgram(Program program) {
		this.program = program;
	}
}
