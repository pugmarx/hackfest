package org.hib.sample;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

public class AuditInterceptor extends EmptyInterceptor {
	private static final long serialVersionUID = 4541852904884956286L;
	private int updates;
	private int creates;
	private int loads;

	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		System.out.println("** delete audited ***");
	}

	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {

		boolean done1 = false;
		boolean done2 = false;
		if (entity instanceof Auditable) {
			updates++;

			for (int i = 0; i < propertyNames.length; i++) {
				if ("updatedOn".equals(propertyNames[i])) {
					currentState[i] = new Date();
					done1 = true;
				}
				if ("updatedBy".equals(propertyNames[i])) {
					// FIXME get the current user here
					currentState[i] = "Mr. Updater";
					done2 = true;
				}
			}
		}
		System.out.println("** flush audited ***");
		return done1 && done2;
	}

	public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if (entity instanceof Auditable) {
			loads++;
		}
		System.out.println("** loading audited ***");
		return false;
	}

	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		System.out.println("*** Before update entity" + entity);
		boolean done1 = false;
		boolean done2 = false;

		if (entity instanceof Auditable) {
			creates++;
			for (int i = 0; i < propertyNames.length; i++) {
				if ("createdOn".equals(propertyNames[i])) {
					state[i] = new Date();
					done1 = true;
				}
				if ("createdBy".equals(propertyNames[i])) {
					state[i] = "Mr. Creater";
					done2 = true;
				}
			}
		}
		System.out.println("** save audited ***");
		System.out.println("*** after update entity" + entity);
		return done1 && done2;
	}

	public void afterTransactionCompletion(Transaction tx) {
		if (tx.wasCommitted()) {
			System.out.println("*** Creations: " + creates + ", Updates: " + updates + ", Loads: " + loads + "***");
		}
		updates = 0;
		creates = 0;
		loads = 0;
	}

}
