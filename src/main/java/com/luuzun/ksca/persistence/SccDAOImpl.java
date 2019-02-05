package com.luuzun.ksca.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.luuzun.ksca.domain.SCC;
import com.luuzun.ksca.utill.FieldToMapUtill;

@Repository
public class SccDAOImpl implements SccDAO{
	
	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.luuzun.ksca.persistence.SccDAO.";
	
	@Override
	public List<SCC> listAll() throws Exception {
		return sqlSession.selectList(namespace+"listAll");
	}

	@Override
	public SCC read(String areaCode, String branchCode, String sccCode) throws Exception {
		Map<String, String> code = new HashMap<>();
		code.put("areaCode", areaCode);
		code.put("branchCode", branchCode);
		code.put("sccCode", sccCode);
		return sqlSession.selectOne(namespace+"read",code);
	}

	@Override
	public void create(SCC scc) throws Exception {
		sqlSession.insert(namespace+"create", scc);
	}

	@Override
	public void update(String destAreaCode, String destBranchCode, String destSccCode, SCC scc) throws Exception {
		Map<String, String> update = new HashMap<>();
		update.put("destAreaCode", destAreaCode);
		update.put("destBranchCode", destBranchCode);
		update.put("destSccCode", destSccCode);
		update = FieldToMapUtill.getInstance().putAllField(update, scc);
		
		System.out.println();
		System.out.println();
		System.out.println(update);
		System.out.println();
		System.out.println();
		
		sqlSession.update(namespace+"update", update);
	}

	@Override
	public void delete(String areaCode, String branchCode, String sccCode) throws Exception {
		Map<String, String> code = new HashMap<>();
		code.put("areaCode", areaCode);
		code.put("branchCode", branchCode);
		code.put("sccCode", sccCode);
		sqlSession.delete(namespace+"delete", code);
	}

	@Override
	public List<SCC> readByAreaCode(String areaCode) {
		return sqlSession.selectList(namespace+"readByAreaCode",areaCode);
	}

	@Override
	public List<SCC> readByBranchCode(String areaCode, String branchCode) {
		Map<String, String> code = new HashMap<>();
		code.put("areaCode", areaCode);
		code.put("branchCode", branchCode);
		
		return sqlSession.selectOne(namespace+"readByBranchCode",code);
	}
}
