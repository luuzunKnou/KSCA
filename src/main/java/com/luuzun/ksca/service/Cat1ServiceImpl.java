package com.luuzun.ksca.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luuzun.ksca.domain.Cat1;
import com.luuzun.ksca.domain.Cat1HasCat2;
import com.luuzun.ksca.persistence.Cat1DAO;
import com.luuzun.ksca.persistence.Cat2DAO;

@Service
public class Cat1ServiceImpl implements Cat1Service{

	@Inject
	private Cat1DAO dao;

	@Inject
	private Cat2DAO cat2Dao;

	
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
	public String delete(String code) throws Exception{
		
		//하위 Cat2 존재하면 삭제 불가
		if(cat2Dao.readByCat1(code).size()!=0) {
			return "ERROR:cascade";
		}
		
		dao.delete(code);
		return code;
	}

	@Override
	public List<Cat1HasCat2> listCat1HasCat2() throws Exception {
		return dao.listCat1HasCat2();
	}
}
