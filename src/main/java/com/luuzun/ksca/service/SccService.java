package com.luuzun.ksca.service;

import java.util.List;

import com.luuzun.ksca.domain.SCC;

public interface SccService {
	public List<SCC> listAll() throws Exception;
	public SCC read(String code) throws Exception;
	public void create(SCC scc) throws Exception;
	public void update(SCC scc) throws Exception;
	public void delete(String code) throws Exception;
	public List<SCC> readByManager(String manager);
}
