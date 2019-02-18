package com.luuzun.ksca.service;

import java.util.List;

import com.luuzun.ksca.domain.Offer;

public interface OfferService {
	public List<Offer> listAll() throws Exception;
	public Offer read(String code) throws Exception;
	public String create(Offer offer) throws Exception;
	public void update(Offer offer) throws Exception;
	public void delete(String code) throws Exception;
	public List<Offer> readByAreaCode(String areaCode);
	public Offer readForExistCheck(String areaCode, String branchCode, String sccCode, String program, String regMonth);
	public void updateMonthlyOper(String offerCode, int monthlyOper);
}