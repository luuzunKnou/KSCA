package com.luuzun.ksca.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luuzun.ksca.domain.OfferProgram;
import com.luuzun.ksca.domain.OfferProgramJoinForList;
import com.luuzun.ksca.persistence.OfferProgramDAO;

@Service
public class OfferProgramServiceImpl implements OfferProgramService{

	@Inject
	private OfferProgramDAO dao;

	@Override
	public OfferProgram read(String code) throws Exception {
		return dao.read(code);
	}

	@Override
	public OfferProgram create(OfferProgram offerProgram) throws Exception {
		return dao.create(offerProgram);
	}

	@Override
	public void update(OfferProgram offerProgram) throws Exception {
		dao.update(offerProgram);
	}

	@Override
	public void delete(String code) throws Exception {
		dao.delete(code);
	}

	@Override
	public List<OfferProgramJoinForList> readOfferProgramJoinForList(String areaCode, String regMonth) {
		return dao.readOfferProgramJoinForList(areaCode, regMonth);
	}

	@Override
	public OfferProgram readForCheck(String program, String regMonth) {
		return dao.readForCheck(program, regMonth);
	}

	@Override
	public List<OfferProgram> readByRegMonth(String areaCode, String regMonth) {
		return dao.readByRegMonth(areaCode, regMonth);
	}
}
