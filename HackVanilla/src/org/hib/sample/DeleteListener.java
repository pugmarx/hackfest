package org.hib.sample;

import java.util.Date;

import org.hibernate.event.PreDeleteEvent;
import org.hibernate.event.PreDeleteEventListener;

public class DeleteListener implements PreDeleteEventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8143155775210751467L;

	@Override
	public boolean onPreDelete(PreDeleteEvent event) {
		System.out.println("------------- inside predelete ---------------");
		BaseEntity entity = (BaseEntity) event.getEntity();
		System.out.println("entity before " + entity);
		entity.setUpdatedBy("Mr. Deleter");
		entity.setUpdatedOn(new Date());
		System.out.println("entity after " + entity);
		return true;
	}

}
