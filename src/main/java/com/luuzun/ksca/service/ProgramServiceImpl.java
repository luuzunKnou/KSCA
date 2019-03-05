package com.luuzun.ksca.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luuzun.ksca.domain.Program;
import com.luuzun.ksca.domain.ProgramJoinForList;
import com.luuzun.ksca.persistence.OfferProgramDAO;
import com.luuzun.ksca.persistence.ProgramDAO;

@Service
public class ProgramServiceImpl implements ProgramService{

	@Inject	private ProgramDAO dao;
	@Inject	private OfferProgramDAO offerProgramDAO;
	
	@Override
	public List<Program> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public Program read(String code) throws Exception{
		return dao.read(code);
	}

	@Override
	public String create(Program program) throws Exception{
		return dao.create(program);
	}

	@Override
	public void update(Program program) throws Exception{
		dao.update(program);
	}

	@Override
	public Program delete(String code) throws Exception{
		Program program = new Program();
		program.setCode(code);
		
		if(offerProgramDAO.readByProgram(code).size()!=0) {
			program.setCode("ERROR:cascade");
			return program;
		}
		
		dao.delete(code);
		return program;
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
