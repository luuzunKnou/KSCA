package com.luuzun.ksca.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.luuzun.ksca.domain.Cat2;
import com.luuzun.ksca.utill.FieldToMapUtill;

@Repository
public class Cat2DAOImpl implements Cat2DAO{
	
	@Inject 
	private SqlSession sqlSession;
	private static final String namespace = "com.luuzun.ksca.persistence.Cat2DAO.";
	
	@Override
	public List<Cat2> listAll() throws Exception {
		return sqlSession.selectList(namespace+"listAll");
	}

	@Override
	public Cat2 read(String code, String cat1) throws Exception {
		Map<String, String> param = new HashMap<>();
		param.put("code", code);
		param.put("cat1", cat1);
		
		return sqlSession.selectOne(namespace+"read",param);
	}

	@Override
	public void create(Cat2 cat2) throws Exception {
		sqlSession.insert(namespace+"create", cat2);
	}

	@Override
	public void update(String destCode, String destCat1, Cat2 cat2) throws Exception {
		Map<String, String> param = new HashMap<>();
		param.put("destCode", destCode);
		param.put("destCat1", destCat1);
		param = FieldToMapUtill.getInstance().putAllField(param, cat2);
		
		sqlSession.update(namespace+"update", param);
	}

	@Override
	public void delete(String code, String cat1) throws Exception {
		Map<String, String> param = new HashMap<>();
		param.put("code", code);
		param.put("cat1", cat1);
		
		sqlSession.delete(namespace+"delete", param);
	}

	@Override
	public List<Cat2> readByCat1(String code) {
		return sqlSession.selectList(namespace+"readByCat1", code);
	}
}
