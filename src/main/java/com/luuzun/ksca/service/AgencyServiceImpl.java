package com.luuzun.ksca.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luuzun.ksca.domain.Agency;
import com.luuzun.ksca.persistence.AgencyDAO;

@Service
public class AgencyServiceImpl implements AgencyService{

	@Inject
	private AgencyDAO dao;

	@Override
	public List<Agency> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public Agency read(String code) throws Exception{
		return dao.read(code);
	}

	@Override
	public String create(Agency agency) throws Exception{
		return dao.create(agency);
	}

	@Override
	public void update(Agency agency) throws Exception{
		dao.update(agency);
	}

	@Override
	public void delete(String code) throws Exception{
		dao.delete(code);
	}

	@Override
	public List<Agency> readByAreaCode(String areaCode) {
		return dao.readByAreaCode(areaCode);
	}
}
