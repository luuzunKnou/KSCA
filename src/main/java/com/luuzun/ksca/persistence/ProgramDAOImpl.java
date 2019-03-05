package com.luuzun.ksca.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.luuzun.ksca.domain.Cat2;
import com.luuzun.ksca.domain.Program;
import com.luuzun.ksca.domain.ProgramJoinForList;

@Repository
public class ProgramDAOImpl implements ProgramDAO{
	
	@Inject //자동 주입
	private SqlSession sqlSession;
	private static final String namespace = "com.luuzun.ksca.persistence.ProgramDAO.";
	
	@Override
	public List<Program> listAll() throws Exception {
		return sqlSession.selectList(namespace+"listAll");
	}

	@Override
	public Program read(String code) throws Exception {
		return sqlSession.selectOne(namespace+"read",code);
	}

	@Override
	public String create(Program program) throws Exception {
		sqlSession.insert(namespace+"create", program);
		return program.getCode();
	}

	@Override
	public void update(Program program) throws Exception {
		sqlSession.update(namespace+"update", program);
	}

	@Override
	public void delete(String code) throws Exception {
		sqlSession.delete(namespace+"delete", code);
	}

	@Override
	public List<Program> readByAreaCode(String areaCode) {
		return sqlSession.selectList(namespace+"readByAreaCode",areaCode);
	}

	@Override
	public List<ProgramJoinForList> readProgramJoinForList(String areaCode) {
		return sqlSession.selectList(namespace+"readProgramJoinForList",areaCode);
	}

	@Override
	public ProgramJoinForList readProgramJoinByCode(String code) {
		return sqlSession.selectOne(namespace+"readProgramJoinByCode",code);
	}

	@Override
	public List<Cat2> readByCat2(Cat2 cat2) {
		Map<String, String> param = new HashMap<>();
		param.put("cat1", cat2.getCat1());
		param.put("cat2", cat2.getCode());

		return sqlSession.selectList(namespace+"readByCat2",param);
	}
}
