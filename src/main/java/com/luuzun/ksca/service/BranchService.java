package com.luuzun.ksca.service;

import java.util.List;

import com.luuzun.ksca.domain.Branch;

public interface BranchService {
	public List<Branch> listAll() throws Exception;
	public Branch read(String areaCode, String branchCode) throws Exception;
	public void create(Branch branch) throws Exception;
	public void update(String destAreaCode, String destBranchCode, Branch branch) throws Exception;
	public String delete(String areaCode, String branchCode) throws Exception;
	public List<Branch> readByAreaCode(String areaCode);
}