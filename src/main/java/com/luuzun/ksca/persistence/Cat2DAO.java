package com.luuzun.ksca.persistence;

import java.util.List;

import com.luuzun.ksca.domain.Cat2;

public interface Cat2DAO {
	public List<Cat2> listAll() throws Exception;
	public Cat2 read(String code, String cat1) throws Exception;
	public void create(Cat2 cat2) throws Exception;
	public void update(String destCode, String destCat1, Cat2 cat2) throws Exception;
	public void delete(String code, String cat1) throws Exception;
}
