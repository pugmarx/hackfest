package org.hib.sample;

import org.hibernate.envers.RevisionListener;

public class GREntityAuditListener implements RevisionListener {
	public void newRevision(Object revisionEntity) {
		GRRevEntity exampleRevEntity = (GRRevEntity) revisionEntity;
		// Identity identity = (Identity)
		// Component.getInstance("org.jboss.seam.security.identity");

		exampleRevEntity.setActor("Bob");
	}
}