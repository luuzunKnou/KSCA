package com.luuzun.ksca.persistence;

import java.util.List;
import java.util.Map;

import com.luuzun.ksca.domain.Schedule;
import com.luuzun.ksca.domain.ScheduleJoinforList;

public interface ScheduleDAO {
	public List<Schedule> listAll() throws Exception;
	public Schedule read(String code) throws Exception;
	public String create(Schedule schedule) throws Exception;
	public void update(Schedule schedule) throws Exception;
	public void delete(String code) throws Exception;

	public List<ScheduleJoinforList> scheduleJoinforList(String areaCode, String thisMonth, String thisYear);
	public void createMany (List<Schedule> scheduleList) throws Exception;
	
	public void updateByOffer(String offer, Schedule schedule) throws Exception;
	public void deleteByOffer(String offer) throws Exception;
	public int checkDuplicate(String offerCode, Schedule schedule);
	public List<Map<String, Object>> readMonthList(String areaCode);
	
}
