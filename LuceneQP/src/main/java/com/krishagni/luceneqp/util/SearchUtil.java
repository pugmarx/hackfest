package com.krishagni.luceneqp.util;

import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.impl.FullTextSessionImpl;

public class SearchUtil {
	public static void indexAll() {
		try {
			Session session = HibUtil.getSessionFactory().getCurrentSession();
			FullTextSession ftSession = new FullTextSessionImpl(session);

			ftSession.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
