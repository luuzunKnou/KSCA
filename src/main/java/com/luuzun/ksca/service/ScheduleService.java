package com.luuzun.ksca.service;

import java.util.List;

import com.luuzun.ksca.domain.Schedule;

public interface ScheduleService {
	public List<Schedule> listAll() throws Exception;
	public Schedule read(String code) throws Exception;
	public String create(Schedule schedule) throws Exception;
	public void update(Schedule schedule) throws Exception;
	public void delete(String code) throws Exception;
}