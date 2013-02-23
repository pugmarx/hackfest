package org.hib.sample;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.hibernate.encryptor.HibernatePBEEncryptorRegistry;

public class HibUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	// private static final String PGSQLCONFIG="pgsql.cfg.xml";
	private static final String MYSQLCONFIG = "mysql.cfg.xml";

	private static SessionFactory buildSessionFactory() {
		try {
			StandardPBEStringEncryptor strongEncryptor = new StandardPBEStringEncryptor();
			strongEncryptor.setPassword("grp$wd");
			
			HibernatePBEEncryptorRegistry registry = HibernatePBEEncryptorRegistry.getInstance();
			registry.registerPBEStringEncryptor("strongHibernateStringEncryptor", strongEncryptor);

			// return new Configuration().configure().buildSessionFactory();
			Configuration c = new Configuration().setInterceptor(new AuditInterceptor());
			return c.configure(MYSQLCONFIG).buildSessionFactory();
			// return new
			// Configuration().configure(MYSQLCONFIG).buildSessionFactory();
		} catch (Exception e) {
			System.out.println("SessionFactory creation failed" + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
