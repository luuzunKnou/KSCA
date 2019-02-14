package com.luuzun.ksca.domain;

public class ScheduleJoinforList {
	private Offer offer;
	private SCC scc;
	private Schedule schedule;
	private Program program;
	
	@Override
	public String toString() {
		return String.format("%nOfferJoinforList [%n**offer=%s, %n**scc=%s, %n**schedule=%s, %n**program=%s]", offer, scc, schedule,
				program);
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
	public Program getProgram() {
		return program;
	}
	public void setProgram(Program program) {
		this.program = program;
	}
}
