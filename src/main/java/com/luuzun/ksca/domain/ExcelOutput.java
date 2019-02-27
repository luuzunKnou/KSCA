package com.luuzun.ksca.domain;

public class ExcelOutput {
	private SCC scc;
	private Cat1 cat1;
	private Cat2 cat2;
	private Program program;
	private OfferProgram offerprogram;
	private Offer offer;
	private Agency agency;
	
	@Override
	public String toString() {
		return String.format("\nExcelOutput [\nscc=%s, \ncat1=%s, \ncat2=%s, \nprogram=%s, \nofferprogram=%s, \noffer=%s, \nagency=%s]\n\n",
				scc, cat1, cat2, program, offerprogram, offer, agency);
	}
	
	public SCC getScc() {
		return scc;
	}
	public void setScc(SCC scc) {
		this.scc = scc;
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
	public Program getProgram() {
		return program;
	}
	public void setProgram(Program program) {
		this.program = program;
	}
	public OfferProgram getOfferprogram() {
		return offerprogram;
	}
	public void setOfferprogram(OfferProgram offerprogram) {
		this.offerprogram = offerprogram;
	}
	public Offer getOffer() {
		return offer;
	}
	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	public Agency getagency() {
		return agency;
	}
	public void setagency(Agency agency) {
		this.agency = agency;
	}
}
