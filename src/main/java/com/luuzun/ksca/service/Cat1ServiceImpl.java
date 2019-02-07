package com.luuzun.ksca.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luuzun.ksca.domain.Cat1;
import com.luuzun.ksca.persistence.Cat1DAO;

@Service
public class Cat1ServiceImpl implements Cat1Service{

	@Inject
	private Cat1DAO dao;

	@Override
	public List<Cat1> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public Cat1 read(String code) throws Exception{
		return dao.read(code);
	}

	@Override
	public void create(Cat1 cat1) throws Exception{
		dao.create(cat1);
	}

	@Override
	public void update(String destCode, Cat1 cat1) throws Exception{
		dao.update(destCode, cat1);
	}

	@Override
	public void delete(String code) throws Exception{
		dao.delete(code);
	}
}
