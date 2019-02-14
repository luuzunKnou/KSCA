package com.luuzun.ksca.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luuzun.ksca.domain.Offer;
import com.luuzun.ksca.persistence.OfferDAO;

@Service
public class OfferServiceImpl implements OfferService{

	@Inject
	private OfferDAO dao;

	@Override
	public List<Offer> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public Offer read(String code) throws Exception{
		return dao.read(code);
	}

	@Override
	public String create(Offer offer) throws Exception{
		return dao.create(offer);
	}

	@Override
	public void update(Offer offer) throws Exception{
		dao.update(offer);
	}

	@Override
	public void delete(String code) throws Exception{
		dao.delete(code);
	}

	@Override
	public List<Offer> readByAreaCode(String areaCode) {
		return dao.readByAreaCode(areaCode);
	}
}