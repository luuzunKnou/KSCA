package com.luuzun.ksca.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.luuzun.ksca.domain.Area;

@Repository //DAO를 스프링에 인식시키기 위해 사용
public class AreaDAOImpl implements AreaDAO{
	
	@Inject //자동 주입
	private SqlSession sqlSession;
	private static final String namespace = "com.luuzun.ksca.persistence.AreaDAO.";
	
	@Override
	public List<Area> listAll() throws Exception {
		return sqlSession.selectList(namespace+"listAll");
	}

	@Override
	public Area read(String code) throws Exception {
		return sqlSession.selectOne(namespace+"read",code);
	}

	@Override
	public void create(Area area) throws Exception {
		sqlSession.insert(namespace+"create", area);
	}

	@Override
	public void update(Area area) throws Exception {
		sqlSession.update(namespace+"update", area);
	}

	@Override
	public void delete(String code) throws Exception {
		sqlSession.delete(namespace+"delete", code);
	}
}
