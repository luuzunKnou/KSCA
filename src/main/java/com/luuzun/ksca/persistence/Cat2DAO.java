package com.luuzun.ksca.persistence;

import java.util.List;

import com.luuzun.ksca.domain.Cat2;

public interface Cat2DAO {
	public List<Cat2> selectChildrenCategoryList() throws Exception;
}
