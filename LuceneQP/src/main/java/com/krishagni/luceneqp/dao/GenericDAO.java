package com.krishagni.luceneqp.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

public interface GenericDAO extends Serializable {

	<T> void save(T r);

	<T> void update(T r);

	<T> void delete(Class<T> klass, Serializable id);

	<T> T findById(Class<T> klass, Serializable id);

	<T> List<T> getList(Criterion... criterion);

	<T> List<T> getList(Session sess, Criterion... criterion);

	<T> int getCount(Criterion... criterion);


}
