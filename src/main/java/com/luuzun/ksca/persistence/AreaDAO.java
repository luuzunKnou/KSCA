package com.luuzun.ksca.persistence;

import java.util.List;

import com.luuzun.ksca.domain.Area;

public interface AreaDAO {
	public List<Area> listAll() throws Exception;
	public Area read(String code) throws Exception;
	public void create(Area area) throws Exception;
	public void update(String destCode, Area area) throws Exception;
	public void delete(String code) throws Exception;
	public List<Area> readByManager(String manager);
}
