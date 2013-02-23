package com.krishagni.hackfest.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.krishagni.hackfest.entities.Participant;
import com.krishagni.hackfest.util.HibUtil;

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
		Transaction tx = sess.beginTransaction();
		Participant r = (Participant) sess.get(Participant.class, id);
		tx.commit();
		sess.flush();
		sess.clear();
		sess.close();
		return r;
	}

}
