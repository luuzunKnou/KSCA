package com.luuzun.ksca.persistence;

import java.util.List;

import com.luuzun.ksca.domain.Category;

public interface CategoryDAO {
	public List<Category> listAll() throws Exception;

}
