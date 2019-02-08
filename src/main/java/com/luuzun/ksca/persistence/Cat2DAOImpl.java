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
		Map<String, String> codeMap = new HashMap<>();
		codeMap.put("code", code);
		codeMap.put("cat1", cat1);
		
		return sqlSession.selectOne(namespace+"read",codeMap);
	}

	@Override
	public void create(Cat2 cat2) throws Exception {
		sqlSession.insert(namespace+"create", cat2);
	}

	@Override
	public void update(String destCode, String destCat1, Cat2 cat2) throws Exception {
		Map<String, String> update = new HashMap<>();
		update.put("destCode", destCode);
		update.put("destCat1", destCat1);
		update = FieldToMapUtill.getInstance().putAllField(update, cat2);
		
		sqlSession.update(namespace+"update", update);
	}

	@Override
	public void delete(String code, String cat1) throws Exception {
		Map<String, String> codeMap = new HashMap<>();
		codeMap.put("code", code);
		codeMap.put("cat1", cat1);
		
		sqlSession.delete(namespace+"delete", codeMap);
	}

	@Override
	public List<Cat2> readByCat1(String code) {
		return sqlSession.selectList(namespace+"readByCat1", code);
	}
}
