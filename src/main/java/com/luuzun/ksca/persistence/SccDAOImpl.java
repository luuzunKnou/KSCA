package com.luuzun.ksca.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.luuzun.ksca.domain.SCC;

@Repository
public class SccDAOImpl implements SccDAO{
	
	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.luuzun.ksca.persistence.SccDAO.";
	
	@Override
	public List<SCC> listAll() throws Exception {
		return sqlSession.selectList(namespace+"listAll");
	}

	@Override
	public SCC read(String code) throws Exception {
		return sqlSession.selectOne(namespace+"read",code);
	}

	@Override
	public void create(SCC scc) throws Exception {
		sqlSession.insert(namespace+"create", scc);
	}

	@Override
	public void update(SCC scc) throws Exception {
		sqlSession.update(namespace+"update", scc);
	}

	@Override
	public void delete(String code) throws Exception {
		sqlSession.delete(namespace+"delete", code);
	}

	@Override
	public List<SCC> readByManager(String manager) {
		return sqlSession.selectList(namespace+"readByManager",manager);
	}

	@Override
	public int countByArea(String area) {
		return sqlSession.selectOne(namespace+"countByArea",area);
	}
}
