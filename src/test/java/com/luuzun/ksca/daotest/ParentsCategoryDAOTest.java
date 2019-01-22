package com.luuzun.ksca.daotest;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.luuzun.ksca.persistence.Cat1DAO;

@RunWith(SpringJUnit4ClassRunner.class) //Spring loading
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) //Spring loading
public class ParentsCategoryDAOTest {
	@Inject
	private Cat1DAO dao;
	private static Logger logger = LoggerFactory.getLogger(ParentsCategoryDAOTest.class);

	@Test
	public void test() throws Exception{
		logger.info(dao.selectCat1List().toString());
	}
}
