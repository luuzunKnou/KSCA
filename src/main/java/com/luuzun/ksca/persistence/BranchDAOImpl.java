package com.luuzun.ksca.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.luuzun.ksca.domain.Branch;
import com.luuzun.ksca.utill.FieldToMapUtill;

@Repository
public class BranchDAOImpl implements BranchDAO{
	
	@Inject //자동 주입
	private SqlSession sqlSession;
	private static final String namespace = "com.luuzun.ksca.persistence.BranchDAO.";
	
	@Override
	public List<Branch> listAll() throws Exception {
		return sqlSession.selectList(namespace+"listAll");
	}

	@Override
	public Branch read(String areaCode, String branchCode) throws Exception {
		Map<String, String> code = new HashMap<>();
		code.put("areaCode", areaCode);
		code.put("branchCode", branchCode);
		
		return sqlSession.selectOne(namespace+"read",code);
	}

	@Override
	public void create(Branch branch) throws Exception {
		sqlSession.insert(namespace+"create", branch);
	}

	@Override
	public void update(String destAreaCode, String destBranchCode, Branch branch) throws Exception {
		Map<String, String> update = new HashMap<>();
		update.put("destAreaCode", destAreaCode);
		update.put("destBranchCode", destBranchCode);
		update = FieldToMapUtill.getInstance().putAllField(update, branch);

		sqlSession.update(namespace+"update", update);
	}

	@Override
	public void delete(String areaCode, String branchCode) throws Exception {
		Map<String, String> code = new HashMap<>();
		code.put("areaCode", areaCode);
		code.put("branchCode", branchCode);
		
		sqlSession.delete(namespace+"delete", code);
	}

	@Override
	public List<Branch> readByAreaCode(String areaCode) {
		return sqlSession.selectList(namespace+"readByAreaCode",areaCode);
	}
}
