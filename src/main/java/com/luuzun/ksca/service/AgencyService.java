package com.luuzun.ksca.service;

import java.util.List;

import com.luuzun.ksca.domain.Agency;

public interface AgencyService {
	public List<Agency> listAll() throws Exception;
	public Agency read(String code) throws Exception;
	public int create(Agency agency) throws Exception;
	public void update(Agency agency) throws Exception;
	public void delete(String code) throws Exception;
	public List<Agency> readByAreaCode(String areaCode);
}