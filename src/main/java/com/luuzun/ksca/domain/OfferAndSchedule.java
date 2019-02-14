package com.luuzun.ksca.domain;

public class OfferAndSchedule {
	private Offer offer;
	private Schedule schedule;
	
	@Override
	public String toString() {
		return String.format("OfferAndSchedule [offer=%s, schedule=%s]", offer, schedule);
	}
	
	public Offer getOffer() {
		return offer;
	}
	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	
	
}
