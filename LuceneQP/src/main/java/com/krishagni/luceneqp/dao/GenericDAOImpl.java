package com.krishagni.luceneqp.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;

import com.krishagni.luceneqp.util.HibUtil;

public class GenericDAOImpl implements GenericDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;

	public GenericDAOImpl() {
		sessionFactory = HibUtil.getSessionFactory();
	}

	private Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		if (!session.isOpen()) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	@Override
	public <T> void save(T r) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.save(r);
		tx.commit();
	}

	@Override
	public <T> void update(T r) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(r);
		tx.commit();
	}

	@Override
	public <T> void delete(Class<T> klass, Serializable id) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.delete(findById(klass, id));
		tx.commit();
	}

	@Override
	public <T> T findById(Class<T> klass, Serializable id) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		T t = (T) session.get(klass, id);
		tx.commit();
		return t;
	}

	@Override
	public <T> List<T> getList(Criterion... criterion) {

		return null;
	}

	@Override
	public <T> List<T> getList(Session sess, Criterion... criterion) {
		return null;
	}

	@Override
	public <T> int getCount(Criterion... criterion) {

		return 0;
	}

}
