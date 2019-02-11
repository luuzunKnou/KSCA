package com.luuzun.ksca.persistence;

import java.util.List;

import com.luuzun.ksca.domain.Program;

public interface ProgramDAO {
	public List<Program> listAll() throws Exception;
	public Program read(String code) throws Exception;
	public int create(Program program) throws Exception;
	public void update(Program program) throws Exception;
	public void delete(String code) throws Exception;
	public List<Program> readByAreaCode(String areaCode);
}
