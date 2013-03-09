package com.krishagni.luceneqp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;

import com.krishagni.luceneqp.entity.Participant;
import com.krishagni.luceneqp.util.HibUtil;

public class ParticipantDAOImpl implements ParticipantDAO {

	@Override
	public void saveParticipant(Participant r) {
		Session sess = HibUtil.getSessionFactory().openSession();
		Transaction tx = sess.beginTransaction();
		// sess.save(r);
		sess.saveOrUpdate(r);
		tx.commit();
		sess.flush();
		sess.clear();
		sess.close();
	}

	@Override
	public void updateParticipant(Participant r) {
		Session sess = HibUtil.getSessionFactory().openSession();
		Transaction tx = sess.beginTransaction();
		// sess.update(r);
		sess.saveOrUpdate(r);
		tx.commit();
		sess.flush();
		sess.clear();
		sess.close();
	}

	@Override
	public void deleteParticipant(Integer r) {
		Session sess = HibUtil.getSessionFactory().openSession();
		Transaction tx = sess.beginTransaction();
		Participant role = (Participant) findById(r);
		sess.delete(role);
		tx.commit();
		sess.flush();
		sess.clear();
		sess.close();
	}

	@Override
	public Participant findById(Integer id) {
		Session sess = HibUtil.getSessionFactory().openSession();
		// Transaction tx = sess.beginTransaction();
		Participant r = (Participant) sess.get(Participant.class, id);
		// tx.commit();
		sess.flush();
		sess.clear();
		sess.close();
		return r;
	}

	@Override
	public List<Participant> getList(Criterion... criterion) {
		Session sess = HibUtil.getSessionFactory().openSession();
		Criteria criteria = sess.createCriteria(Participant.class);
		for (Criterion c : criterion) {
			criteria.add(c);
		}
		sess.close();
		return criteria.list();
	}

	public List<Participant> getList(Session sess, Criterion... criterion) {
		// Session sess = HibUtil.getSessionFactory().openSession();
		Criteria criteria = sess.createCriteria(Participant.class);
		for (Criterion c : criterion) {
			criteria.add(c);
		}
		// sess.close();
		return criteria.list();
	}

	@Override
	public int getCount(Criterion... criterion) {
		System.out.println("-tbd-");
		return 0;
	}

	// @Override
	// public List<Participant> getByCountry(String country) {
	// Session sess = HibUtil.getSessionFactory().openSession();
	// Transaction tx = sess.beginTransaction();
	//
	// sess.close();
	// return null;
	// }

}
