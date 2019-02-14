package com.luuzun.ksca.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.luuzun.ksca.domain.Schedule;

@Repository
public class ScheduleDAOImpl implements ScheduleDAO{
	
	@Inject //자동 주입
	private SqlSession sqlSession;
	private static final String namespace = "com.luuzun.ksca.persistence.ScheduleDAO.";
	
	@Override
	public List<Schedule> listAll() throws Exception {
		return sqlSession.selectList(namespace+"listAll");
	}

	@Override
	public Schedule read(String code) throws Exception {
		return sqlSession.selectOne(namespace+"read",code);
	}

	@Override
	public String create(Schedule schedule) throws Exception {
		sqlSession.insert(namespace+"create", schedule);
		return schedule.getCode();
	}

	@Override
	public void update(Schedule schedule) throws Exception {
		sqlSession.update(namespace+"update", schedule);
	}

	@Override
	public void delete(String code) throws Exception {
		sqlSession.delete(namespace+"delete", code);
	}
}
