package com.luuzun.ksca.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luuzun.ksca.domain.Cat2;
import com.luuzun.ksca.persistence.Cat2DAO;

@Service
public class Cat2ServiceImpl implements Cat2Service{

	@Inject
	private Cat2DAO dao;

	@Override
	public List<Cat2> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public Cat2 read(String code, String cat1) throws Exception{
		return dao.read(code, cat1);
	}

	@Override
	public void create(Cat2 cat2) throws Exception{
		dao.create(cat2);
	}

	@Override
	public void update(String destCode, String destCat1, Cat2 cat2) throws Exception{
		dao.update(destCode, destCat1, cat2);
	}

	@Override
	public void delete(String code, String cat1) throws Exception{
		dao.delete(code, cat1);
	}
}
