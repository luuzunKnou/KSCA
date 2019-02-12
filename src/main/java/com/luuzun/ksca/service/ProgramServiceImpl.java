package com.luuzun.ksca.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luuzun.ksca.domain.Program;
import com.luuzun.ksca.domain.ProgramJoinForList;
import com.luuzun.ksca.persistence.ProgramDAO;

@Service
public class ProgramServiceImpl implements ProgramService{

	@Inject
	private ProgramDAO dao;

	@Override
	public List<Program> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public Program read(String code) throws Exception{
		return dao.read(code);
	}

	@Override
	public int create(Program program) throws Exception{
		return dao.create(program);
	}

	@Override
	public void update(Program program) throws Exception{
		dao.update(program);
	}

	@Override
	public void delete(String code) throws Exception{
		dao.delete(code);
	}

	@Override
	public List<Program> readByAreaCode(String areaCode) {
		return dao.readByAreaCode(areaCode);
	}

	@Override
	public List<ProgramJoinForList> readProgramJoinForList(String areaCode) {
		return dao.readProgramJoinForList(areaCode);
	}

	@Override
	public ProgramJoinForList readProgramJoinByCode(String code) {
		return dao.readProgramJoinByCode(code);
	}
}
