package com.luuzun.ksca.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luuzun.ksca.domain.Schedule;
import com.luuzun.ksca.domain.ScheduleJoinforList;
import com.luuzun.ksca.persistence.ScheduleDAO;

@Service
public class ScheduleServiceImpl implements ScheduleService{

	@Inject
	private ScheduleDAO dao;

	@Override
	public List<Schedule> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public Schedule read(String code) throws Exception{
		return dao.read(code);
	}

	@Override
	public Schedule create(Schedule schedule) throws Exception{
		return dao.create(schedule);
	}

	@Override
	public void update(Schedule schedule) throws Exception{
		dao.update(schedule);
	}

	@Override
	public void delete(String code) throws Exception{
		dao.delete(code);
	}

	@Override
	public List<ScheduleJoinforList> scheduleJoinforList(String areaCode, String thisMonth, String thisYear) {
		return dao.scheduleJoinforList(areaCode, thisMonth, thisYear);
	}

	@Override
	public void createMany(List<Schedule> scheduleList) throws Exception {
		dao.createMany(scheduleList);
	}
	
	@Override
	public void updateByOffer(String offer, Schedule schedule) throws Exception{
		dao.updateByOffer(offer, schedule);
	}

	@Override
	public void deleteByOffer(String offer) throws Exception{
		dao.deleteByOffer(offer);
	}

	@Override
	public int checkDuplicate(String offerCode, Schedule schedule) {
		return dao.checkDuplicate(offerCode, schedule);
	}

	@Override
	public List<Map<String, Object>> readMonthList(String areaCode) {
		return dao.readMonthList(areaCode);
	}

	@Override
	public List<Schedule> readByRegMonth(String areaCode, String regMonth) {
		return dao.readByRegMonth(areaCode, regMonth);
	}
}
