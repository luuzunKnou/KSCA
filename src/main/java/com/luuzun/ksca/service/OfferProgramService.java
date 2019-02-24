package com.luuzun.ksca.service;

import java.util.List;

import com.luuzun.ksca.domain.Offer;
import com.luuzun.ksca.domain.OfferProgram;
import com.luuzun.ksca.domain.OfferProgramJoinForList;

public interface OfferProgramService {
	public OfferProgram read(String code) throws Exception;
	public OfferProgram create(OfferProgram offerProgram) throws Exception;
	public void update(OfferProgram offerProgram) throws Exception;
	public void delete(String code) throws Exception;
	
	public List<OfferProgramJoinForList> readOfferProgramJoinForList(String areaCode, String regMonth);
	
	public OfferProgram readForCheck(String program, String regMonth);
	public List<OfferProgram> readByRegMonth(String areaCode, String regMonth);
}