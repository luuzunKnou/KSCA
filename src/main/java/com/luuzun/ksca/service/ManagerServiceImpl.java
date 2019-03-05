package com.luuzun.ksca.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luuzun.ksca.domain.Area;
import com.luuzun.ksca.domain.Branch;
import com.luuzun.ksca.domain.Manager;
import com.luuzun.ksca.domain.ManagerHasArea;
import com.luuzun.ksca.persistence.AreaDAO;
import com.luuzun.ksca.persistence.BranchDAO;
import com.luuzun.ksca.persistence.ManagerDAO;

@Service
public class ManagerServiceImpl implements ManagerService{

	@Inject	private ManagerDAO dao;
	@Inject	private BranchDAO branchDao;
	@Inject	private AreaDAO areaDao;
	
	
	@Override
	public List<Manager> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public Manager read(String id) throws Exception {
		return dao.read(id);
	}

	@Override
	public Manager readForLogin(String id, String password) throws Exception {
		return dao.readForLogin(id, password);
	}

	@Override
	@Transactional
	public void create(Manager manager, Area area) throws Exception {
		//set area
		area.setCode();
		
		//set manager
		manager.setArea(area.getCode());
		
		//set branch 
		Branch branch = new Branch();
		branch.setAreaCode(area.getCode());
		branch.setBranch("¾øÀ½");
		branch.setBranchCode("99");
		
		areaDao.create(area);
		branchDao.create(branch);
		dao.create(manager);
	}

	@Override
	public void update(Manager manager) throws Exception {
		dao.update(manager);
	}

	@Override
	public void delete(String id) throws Exception {
		dao.delete(id);
	}

	@Override
	public List<Manager> readWaitingManager() {
		return dao.readWaitingManager();
	}

	@Override
	public void updateApproveManager(String id) {
		dao.updateApproveManager(id);
	}

	@Override
	public ManagerHasArea readManagerHasArea(String id) {
		return dao.readManagerHasArea(id);
	}

	@Override
	public void leave(String id) {
		dao.leave(id);
	}

	@Override
	public void rejoin(String id) {
		dao.rejoin(id);
	}


}
