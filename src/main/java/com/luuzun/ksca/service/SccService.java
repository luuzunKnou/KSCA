package com.luuzun.ksca.service;

import java.util.List;

import com.luuzun.ksca.domain.SCC;

public interface SccService {
	public List<SCC> listAll() throws Exception;
	public SCC read(String areaCode, String branchCode, String sccCode) throws Exception;
	public void create(SCC scc) throws Exception;
	public void update(String destAreaCode, String destBranchCode, String destSccCode, SCC scc) throws Exception;
	public void delete(String areaCode, String branchCode, String sccCode) throws Exception;
	public List<SCC> readByAreaCode(String areaCode);
	public List<SCC> readByBranchCode(String areaCode, String branchCode);
}
