package com.luuzun.ksca.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luuzun.ksca.domain.Area;
import com.luuzun.ksca.persistence.AreaDAO;

@Service
public class AreaServiceImpl implements AreaService{

	@Inject
	private AreaDAO dao;

	@Override
	public List<Area> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public Area read(String code) throws Exception{
		return dao.read(code);
	}

	@Override
	public void create(Area area) throws Exception{
		dao.create(area);
	}

	@Override
	public void update(Area area) throws Exception{
		dao.update(area);
	}

	@Override
	public void delete(String code) throws Exception{
		dao.delete(code);
	}

	@Override
	public List<Area> readByManager(String manager) {
		return dao.readByManager(manager);
	}

	@Override
	public String readFrontCode(String manager) {
		return dao.readFrontCode(manager);
	}
}
