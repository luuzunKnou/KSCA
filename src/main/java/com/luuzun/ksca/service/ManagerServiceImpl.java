package com.luuzun.ksca.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luuzun.ksca.domain.Manager;
import com.luuzun.ksca.persistence.ManagerDAO;

@Service
public class ManagerServiceImpl implements ManagerService{

	@Inject
	private ManagerDAO dao;
	
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
	public void create(Manager manager) throws Exception {
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


}
