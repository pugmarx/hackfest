package com.krishagni.luceneqp.search;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.krishagni.luceneqp.dao.GenericDAO;
import com.krishagni.luceneqp.dao.GenericDAOImpl;
import com.krishagni.luceneqp.entity.CollectionProtocolReg;
import com.krishagni.luceneqp.util.DataGenerator;
import com.krishagni.luceneqp.util.HibUtil;
import com.krishagni.luceneqp.util.SearchUtil;

public class SearchTest {

	private GenericDAO dao;

	@Before
	public void setUp() {
		dao = new GenericDAOImpl();
	}

	@Test
	public void rebuildIndex() {
		// SearchUtil su = new SearchUtil();
		SearchUtil.indexAll();
	}

	@Test
	public void saveCPR() {
		CollectionProtocolReg cpr = DataGenerator.getCollectionProtocolReg();
		dao.save(cpr);
	}

	@After
	public void tearDown() {
		HibUtil.getSessionFactory().getCurrentSession().close();
	}
}
