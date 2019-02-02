package com.luuzun.ksca.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luuzun.ksca.domain.SCC;
import com.luuzun.ksca.persistence.SccDAO;

@Service
public class SccServiceImpl implements SccService{

	@Inject
	private SccDAO dao;

	@Override
	public List<SCC> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public SCC read(String code) throws Exception{
		return dao.read(code);
	}

	@Override
	public void create(SCC scc) throws Exception{
		dao.create(scc);
	}

	@Override
	public void update(SCC scc) throws Exception{
		dao.update(scc);
	}

	@Override
	public void delete(String code) throws Exception{
		dao.delete(code);
	}

	@Override
	public List<SCC> readByManager(String manager) {
		return dao.readByManager(manager);
	}

	@Override
	public int countByArea(String area) {
		return dao.countByArea(area);
	}
}
