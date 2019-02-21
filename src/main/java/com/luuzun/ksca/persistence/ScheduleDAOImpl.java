package com.luuzun.ksca.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.luuzun.ksca.domain.Schedule;
import com.luuzun.ksca.domain.ScheduleJoinforList;
import com.luuzun.ksca.utill.FieldToMapUtill;

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

	@Override
	public List<ScheduleJoinforList> scheduleJoinforList(String areaCode, String thisMonth, String thisYear) {
		Map<String, String> param = new HashMap<>();
		param.put("areaCode", areaCode);
		param.put("thisMonth", thisMonth);
		param.put("thisYear", thisYear);
		
		return sqlSession.selectList(namespace+"scheduleJoinforList", param);
	}

	@Override
	public void createMany(List<Schedule> scheduleList) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("scheduleList", scheduleList);
		sqlSession.insert(namespace+"createMany", param);
	}
	
	@Override
	public void updateByOffer(String offer, Schedule schedule) throws Exception {
		Map<String, String> param = new HashMap<>();
		param.put("destOffer", offer);
		param.put("simpleDate", schedule.getSimpleDate());
		param = FieldToMapUtill.getInstance().putAllField(param, schedule);

		sqlSession.update(namespace+"updateByOffer", param);
	}

	@Override
	public void deleteByOffer(String destOffer) throws Exception {
		sqlSession.delete(namespace+"deleteByOffer", destOffer);
	}

	@Override
	public int checkDuplicate(String offerCode, Schedule schedule) {
		Map<String, String> param = new HashMap<>();
		param.put("destOffer", offerCode);
		param.put("simpleDate", schedule.getSimpleDate());
		param = FieldToMapUtill.getInstance().putAllField(param, schedule);

		return sqlSession.selectOne(namespace+"checkDuplicate",param);
	}
}
