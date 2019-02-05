package com.luuzun.ksca.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luuzun.ksca.domain.Branch;
import com.luuzun.ksca.persistence.BranchDAO;

@Service
public class BranchServiceImpl implements BranchService{

	@Inject
	private BranchDAO dao;

	@Override
	public List<Branch> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public Branch read(String areaCode, String branchCode) throws Exception{
		return dao.read(areaCode, branchCode);
	}

	@Override
	public void create(Branch branch) throws Exception{
		dao.create(branch);
	}

	@Override
	public void update(String destAreaCode, String destBranchCode, Branch branch) throws Exception{
		dao.update(destAreaCode, destBranchCode, branch);
	}

	@Override
	public void delete(String areaCode, String branchCode) throws Exception{
		dao.delete(areaCode, branchCode);
	}

	@Override
	public List<Branch> readByAreaCode(String areaCode) {
		return dao.readByAreaCode(areaCode);
	}
}
