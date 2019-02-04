package com.luuzun.ksca.persistence;

import java.util.List;

import com.luuzun.ksca.domain.SCC;

public interface SccDAO {
	public List<SCC> listAll() throws Exception;
	public SCC read(String code) throws Exception;
	public void create(SCC scc) throws Exception;
	public void update(SCC scc) throws Exception;
	public void delete(String code) throws Exception;
	public List<SCC> readByManager(String manager);
	public int countByArea(String area);
}
