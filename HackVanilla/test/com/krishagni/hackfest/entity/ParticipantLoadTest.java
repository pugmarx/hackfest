package com.krishagni.hackfest.entity;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.krishagni.hackfest.dao.ParticipantDAO;
import com.krishagni.hackfest.dao.ParticipantDAOImpl;
import com.krishagni.hackfest.util.DataGenerator;
import com.krishagni.hackfest.util.HibUtil;

public class ParticipantLoadTest {

	ParticipantDAO dao = null;

	@Before
	public void setUp() {
		dao = new ParticipantDAOImpl();
	}

	@Test
	public void cleanParticipants() {
		Session sess = HibUtil.getSessionFactory().openSession();
		Transaction tx = sess.beginTransaction();
		SQLQuery q = sess.createSQLQuery("DELETE FROM PARTICIPANT");
		q.executeUpdate();
		tx.commit();
		sess.close();
	}

	@Test
	public void addParticipant() {
		int n = 10000;
		long sTime = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			dao.saveParticipant(DataGenerator.getRandomParticipant());
		}
		long eTime = System.currentTimeMillis();
		System.out.println(String.format(
				":: Total time to save %d participants:<%dms> ::", n,
				(eTime - sTime)));
	}

	@Test
	public void retrieveNParticipants() {
		// int n = 100;
		Session sess = HibUtil.getSessionFactory().openSession();
		long sTime = System.currentTimeMillis();
		List<Participant> pList = dao.getList(sess,
				Restrictions.eq("country", "India"));
		long eTime = System.currentTimeMillis();
		System.out.println(String.format(
				":: Total time to retrieve %d participants:<%dms> ::",
				pList.size(), (eTime - sTime)));
		sess.clear();
		sess.flush();
		sess.close();
	}

	@After
	public void tearDown() {

	}

}
