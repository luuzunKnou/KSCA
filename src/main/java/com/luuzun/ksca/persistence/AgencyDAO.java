package com.luuzun.ksca.persistence;

import java.util.List;

import com.luuzun.ksca.domain.Agency;

public interface AgencyDAO {
	public List<Agency> listAll() throws Exception;
	public Agency read(String code) throws Exception;
	public String create(Agency agency) throws Exception;
	public void update(Agency agency) throws Exception;
	public void delete(String code) throws Exception;
	public List<Agency> readByAreaCode(String areaCode);
}
