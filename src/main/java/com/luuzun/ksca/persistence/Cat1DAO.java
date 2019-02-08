package com.luuzun.ksca.persistence;

import java.util.List;

import com.luuzun.ksca.domain.Cat1;
import com.luuzun.ksca.domain.Cat1HasCat2;

public interface Cat1DAO {
	public List<Cat1> listAll() throws Exception;
	public Cat1 read(String code) throws Exception;
	public void create(Cat1 cat1) throws Exception;
	public void update(String destCode, Cat1 cat1) throws Exception;
	public void delete(String code) throws Exception;
	
	public List<Cat1HasCat2> listCat1HasCat2() throws Exception;
}
