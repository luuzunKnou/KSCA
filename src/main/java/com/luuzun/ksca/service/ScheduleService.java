package com.luuzun.ksca.service;

import java.util.List;

import com.luuzun.ksca.domain.Schedule;
import com.luuzun.ksca.domain.ScheduleJoinforList;

public interface ScheduleService {
	public List<Schedule> listAll() throws Exception;
	public Schedule read(String code) throws Exception;
	public String create(Schedule schedule) throws Exception;
	public void update(Schedule schedule) throws Exception;
	public void delete(String code) throws Exception;
	
	public List<ScheduleJoinforList> scheduleJoinforList(String areaCode, String thisMonth, String thisYear);
}