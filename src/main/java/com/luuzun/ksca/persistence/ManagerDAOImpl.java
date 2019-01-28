package com.luuzun.ksca.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.luuzun.ksca.domain.Manager;
import com.luuzun.ksca.domain.ManagerHasArea;

@Repository //DAO를 스프링에 인식시키기 위해 사용
public class ManagerDAOImpl implements ManagerDAO{
	
	@Inject //자동 주입
	private SqlSession sqlSession;
	private static final String namespace = "com.luuzun.ksca.persistence.ManagerDAO.";
	
	@Override
	public List<Manager> listAll() throws Exception {
		return sqlSession.selectList(namespace+"listAll");
	}

	@Override
	public Manager read(String id) throws Exception {
		return sqlSession.selectOne(namespace+"read",id);
	}
	
	@Override
	public Manager readForLogin(String id, String password) {
		Map<String, String> managerInfo = new HashMap<>();
		managerInfo.put("id", id);
		managerInfo.put("password", password);
		return sqlSession.selectOne(namespace+"readForLogin", managerInfo);
	}

	@Override
	public void create(Manager manager) {
		sqlSession.insert(namespace+"create", manager);
	}
	
	@Override
	public void update(Manager manager) {
		sqlSession.update(namespace+"update", manager);
	}
	
	@Override
	public void delete(String id) {
		sqlSession.delete(namespace+"delete", id);
	}

	@Override
	public List<Manager> readWaitingManager() {
		return sqlSession.selectList(namespace+"readWaitingManager");
	}

	@Override
	public void updateApproveManager(String id) {
		sqlSession.update(namespace+"updateApproveManager", id);
	}

	@Override
	public ManagerHasArea readManagerHasArea(String id) {
		return sqlSession.selectOne(namespace+"readManagerHasArea",id);
	}

	@Override
	public void leave(String id) {
		sqlSession.update(namespace+"leave", id);
	}

	@Override
	public void rejoin(String id) {
		sqlSession.update(namespace+"rejoin", id);
	}
}
