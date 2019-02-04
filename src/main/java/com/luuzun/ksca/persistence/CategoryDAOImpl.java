package com.luuzun.ksca.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.luuzun.ksca.domain.Category;

@Repository
public class CategoryDAOImpl implements CategoryDAO{
	
	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.luuzun.ksca.persistence.CategoryDAO.";
	@Override
	public List<Category> listAll() throws Exception {
		return sqlSession.selectList(namespace+"listAll");
	}
}
