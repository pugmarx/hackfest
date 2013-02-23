package org.hib.sample;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class RoleDAOImpl implements RoleDAO {

	@Override
	public void saveRole(Role r) {
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
	public void updateRole(Role r) {
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
	public void deleteRole(Integer r) {
		Session sess = HibUtil.getSessionFactory().openSession();
		Transaction tx = sess.beginTransaction();
		Role role = (Role) findById(r);
		sess.delete(role);
		tx.commit();
		sess.flush();
		sess.clear();
		sess.close();
	}

	@Override
	public Role findById(Integer id) {
		Session sess = HibUtil.getSessionFactory().openSession();
		Transaction tx = sess.beginTransaction();
		Role r = (Role) sess.get(Role.class, id);
		tx.commit();
		sess.flush();
		sess.clear();
		sess.close();
		return r;
	}

}
