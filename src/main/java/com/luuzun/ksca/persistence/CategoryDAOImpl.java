package com.luuzun.ksca.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.luuzun.ksca.domain.Category;

@Repository //DAO를 스프링에 인식시키기 위해 사용
public class CategoryDAOImpl implements CategoryDAO{
	
	@Inject //자동 주입
	private SqlSession sqlSession;
	private static final String namespace = "com.luuzun.ksca.persistence.CategoryDAO.";
	@Override
	public List<Category> listAll() throws Exception {
		return sqlSession.selectList(namespace+"listAll");
	}
}
