package com.krishagni.hackfest.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.hibernate4.encryptor.HibernatePBEEncryptorRegistry;

public class HibUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	//private static final String ORACLECFG = "oracle.cfg.xml";
	private static final String MYSQLCFG = "mysql.cfg.xml";

	private static SessionFactory buildSessionFactory() {
		try {
			StandardPBEStringEncryptor strongEncryptor = new StandardPBEStringEncryptor();
			strongEncryptor.setPassword("hackfe$t");

			HibernatePBEEncryptorRegistry registry = HibernatePBEEncryptorRegistry
					.getInstance();
			registry.registerPBEStringEncryptor(
					"strongHibernateStringEncryptor", strongEncryptor);

			// return new Configuration().configure().buildSessionFactory();
			// Configuration c = new Configuration().setInterceptor(new
			// AuditInterceptor());
			// return c.configure(MYSQLCONFIG).buildSessionFactory();
			// return new
			// Configuration().configure(MYSQLCONFIG).buildSessionFactory();

			Configuration configuration = new Configuration();
			//configuration.configure(ORACLECFG);
			configuration.configure(MYSQLCFG);

			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
					.applySettings(configuration.getProperties())
					.buildServiceRegistry();
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

			return sessionFactory;
		} catch (Exception e) {
			System.out.println("SessionFactory creation failed" + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
