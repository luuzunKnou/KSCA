package com.luuzun.ksca.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.luuzun.ksca.domain.Cat1;

@Repository //DAO�� �������� �νĽ�Ű�� ���� ���
public class Cat1DAOImpl implements Cat1DAO{
	
	@Inject //�ڵ� ����
	private SqlSession sqlSession;
	private static final String namespace = "com.luuzun.ksca.persistence.Cat1DAO.";
	
	@Override
	public List<Cat1> selectCat1List(){
		return sqlSession.selectList(namespace+"selectCat1List");
	}
}
