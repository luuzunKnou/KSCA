package com.luuzun.ksca.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.luuzun.ksca.domain.Agency;

@Repository
public class AgencyDAOImpl implements AgencyDAO{
	
	@Inject //자동 주입
	private SqlSession sqlSession;
	private static final String namespace = "com.luuzun.ksca.persistence.AgencyDAO.";
	
	@Override
	public List<Agency> listAll() throws Exception {
		return sqlSession.selectList(namespace+"listAll");
	}

	@Override
	public Agency read(String code) throws Exception {
		return sqlSession.selectOne(namespace+"read",code);
	}

	@Override
	public int create(Agency agency) throws Exception {
		sqlSession.insert(namespace+"create", agency);
		return Integer.parseInt(agency.getCode());
	}

	@Override
	public void update(Agency agency) throws Exception {
		sqlSession.update(namespace+"update", agency);
	}

	@Override
	public void delete(String code) throws Exception {
		sqlSession.delete(namespace+"delete", code);
	}

	@Override
	public List<Agency> readByAreaCode(String areaCode) {
		return sqlSession.selectList(namespace+"readByAreaCode",areaCode);
	}
}
