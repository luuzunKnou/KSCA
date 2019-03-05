package com.luuzun.ksca.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luuzun.ksca.domain.Cat2;
import com.luuzun.ksca.persistence.Cat2DAO;
import com.luuzun.ksca.persistence.ProgramDAO;

@Service
public class Cat2ServiceImpl implements Cat2Service{

	@Inject	private Cat2DAO dao;
	@Inject	private ProgramDAO programDAO;
	
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
	public Cat2 delete(Cat2 cat2) throws Exception{
		//하위 program이 존재하면 삭제 불가
		if(programDAO.readByCat2(cat2).size()!=0) {
			Cat2 error = new Cat2();
			error.setCode("ERROR:cascade");
			return error;
		}
		
		dao.delete(cat2.getCode(), cat2.getCat1());
		return cat2;
	}

	@Override
	public List<Cat2> readByCat1(String code) {
		return dao.readByCat1(code);
	}
}
