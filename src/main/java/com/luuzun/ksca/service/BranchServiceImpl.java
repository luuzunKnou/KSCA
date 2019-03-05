package com.luuzun.ksca.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luuzun.ksca.domain.Branch;
import com.luuzun.ksca.persistence.BranchDAO;
import com.luuzun.ksca.persistence.SccDAO;

@Service
public class BranchServiceImpl implements BranchService{

	@Inject
	private BranchDAO dao;
	@Inject
	private SccDAO sccDao;

	
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
	public String delete(String areaCode, String branchCode) throws Exception{
		
		//99 분회는 삭제 불가
		if(branchCode.equals("99")){
			return "ERROR:def";
		}
		
		//분회에 소속된 경로당이 존재하면 삭제 불가
		if(sccDao.readByBranchCode(areaCode, branchCode).size()!=0) {
			return "ERROR:cascade";
		}
		
		dao.delete(areaCode, branchCode);
		
		return branchCode;
	}

	@Override
	public List<Branch> readByAreaCode(String areaCode) {
		return dao.readByAreaCode(areaCode);
	}
}
