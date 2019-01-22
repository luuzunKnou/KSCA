package com.luuzun.ksca.persistence;

import java.util.List;

import com.luuzun.ksca.domain.Manager;

public interface ManagerDAO {
	public List<Manager> listAll() throws Exception;
	public Manager read(String id) throws Exception;
	public Manager readForLogin(String id, String password) throws Exception;
	public void create(Manager manager) throws Exception;
	public void update(Manager manager) throws Exception;
	public void delete(String id) throws Exception;
	public List<Manager> readWaitingManager();
	public void updateApproveManager(String id);
}
