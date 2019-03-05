package com.luuzun.ksca.service;

import java.util.List;

import com.luuzun.ksca.domain.Cat2;

public interface Cat2Service {
	public List<Cat2> listAll() throws Exception;
	public Cat2 read(String code, String cat1) throws Exception;
	public void create(Cat2 cat2) throws Exception;
	public void update(String destCode, String destCat1, Cat2 cat2) throws Exception;
	public Cat2 delete(Cat2 cat2) throws Exception;
	public List<Cat2> readByCat1(String code);
}
