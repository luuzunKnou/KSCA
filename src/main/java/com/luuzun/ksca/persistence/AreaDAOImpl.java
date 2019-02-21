package com.luuzun.ksca.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.luuzun.ksca.domain.Area;
import com.luuzun.ksca.utill.FieldToMapUtill;

@Repository
public class AreaDAOImpl implements AreaDAO{
	
	@Inject 
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
	public void update(String destCode, Area area) throws Exception {
		Map<String, String> param = new HashMap<>();
		param.put("destCode", destCode);
		param = FieldToMapUtill.getInstance().putAllField(param, area);
		
		sqlSession.update(namespace+"update", param);
	}

	@Override
	public void delete(String code) throws Exception {
		sqlSession.delete(namespace+"delete", code);
	}
}
