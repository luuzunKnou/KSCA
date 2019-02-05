package com.luuzun.ksca.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luuzun.ksca.domain.SCC;
import com.luuzun.ksca.persistence.SccDAO;

@Service
public class SccServiceImpl implements SccService{

	@Inject
	private SccDAO dao;

	@Override
	public List<SCC> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public SCC read(String areaCode, String branchCode, String sccCode) throws Exception{
		return dao.read(areaCode, branchCode, sccCode);
	}

	@Override
	public void create(SCC scc) throws Exception{
		dao.create(scc);
	}

	@Override
	public void update(String destAreaCode, String destBranchCode, String destSccCode, SCC scc) throws Exception{
		dao.update(destAreaCode, destBranchCode, destSccCode, scc);
	}

	@Override
	public void delete(String areaCode, String branchCode, String sccCode) throws Exception{
		dao.delete(areaCode, branchCode, sccCode);
	}

	@Override
	public List<SCC> readByAreaCode(String areaCode) {
		return dao.readByAreaCode(areaCode);
	}

	@Override
	public List<SCC> readByBranchCode(String areaCode, String branchCode) {
		return dao.readByBranchCode(areaCode, branchCode);
	}
}
