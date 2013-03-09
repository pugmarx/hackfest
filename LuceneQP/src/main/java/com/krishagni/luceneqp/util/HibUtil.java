package com.krishagni.luceneqp.util;

import java.io.File;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
//import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
//import org.jasypt.hibernate4.encryptor.HibernatePBEEncryptorRegistry;

public class HibUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	// private static final String ORACLECFG = "oracle.cfg.xml";

	private static final String MYSQLCFG = "/home/syed/dev/hackfest/ref/workspace12mgmt/LuceneQP/src/test/resources/mysql.cfg.xml";

	private static SessionFactory buildSessionFactory() {
		try {
			// //StandardPBEStringEncryptor strongEncryptor = new
			// StandardPBEStringEncryptor();
			// strongEncryptor.setPassword("hackfe$t");
			// strongEncryptor.setAlgorithm("PBEWithMD5AndTripleDES");
			// HibernatePBEEncryptorRegistry registry =
			// HibernatePBEEncryptorRegistry
			// .getInstance();
			// registry.registerPBEStringEncryptor(
			// "strongHibernateStringEncryptor", strongEncryptor);

			// return new Configuration().configure().buildSessionFactory();
			// Configuration c = new Configuration().setInterceptor(new
			// AuditInterceptor());
			// return c.configure(MYSQLCONFIG).buildSessionFactory();
			// return new
			// Configuration().configure(MYSQLCONFIG).buildSessionFactory();
			File f = new File(MYSQLCFG);
			// if(f.isFile()){
			// System.out.println("_-- Found --_");
			// }
			Configuration configuration = new Configuration();

			// Properties luceneProps = new Properties();
			// luceneProps.put("hibernate.search.default.directory_provider",
			// "filesystem");
			// luceneProps.put("hibernate.search.default.indexBase",
			// "/home/syed/dev/lucene/indexes");

			// configuration.configure(ORACLECFG);
			configuration.configure(f);

			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
					.applySettings(configuration.getProperties())
					.buildServiceRegistry();
			SessionFactory sessionFactory = configuration
					.buildSessionFactory(serviceRegistry);

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
