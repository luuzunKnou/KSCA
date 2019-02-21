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
		Map<String, String> param = new HashMap<>();
		param.put("areaCode", areaCode);
		param.put("branchCode", branchCode);
		param.put("sccCode", sccCode);
		return sqlSession.selectOne(namespace+"read",param);
	}

	@Override
	public void create(SCC scc) throws Exception {
		sqlSession.insert(namespace+"create", scc);
	}

	@Override
	public void update(String destAreaCode, String destBranchCode, String destSccCode, SCC scc) throws Exception {
		Map<String, String> param = new HashMap<>();
		param.put("destAreaCode", destAreaCode);
		param.put("destBranchCode", destBranchCode);
		param.put("destSccCode", destSccCode);
		param.put("simpleRegDate",scc.getSimpleRegDate()); //for update SQL formatting
		param = FieldToMapUtill.getInstance().putAllField(param, scc);
		
		sqlSession.update(namespace+"update", param);
	}

	@Override
	public void delete(String areaCode, String branchCode, String sccCode) throws Exception {
		Map<String, String> param = new HashMap<>();
		param.put("areaCode", areaCode);
		param.put("branchCode", branchCode);
		param.put("sccCode", sccCode);
		sqlSession.delete(namespace+"delete", param);
	}

	@Override
	public List<SCC> readByAreaCode(String areaCode) {
		return sqlSession.selectList(namespace+"readByAreaCode",areaCode);
	}

	@Override
	public List<SCC> readByBranchCode(String areaCode, String branchCode) {
		Map<String, String> param = new HashMap<>();
		param.put("areaCode", areaCode);
		param.put("branchCode", branchCode);
		
		return sqlSession.selectList(namespace+"readByBranchCode",param);
	}
}
