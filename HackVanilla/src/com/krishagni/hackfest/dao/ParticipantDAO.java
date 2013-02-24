package com.krishagni.hackfest.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import com.krishagni.hackfest.entity.Participant;

public interface ParticipantDAO {

	void saveParticipant(Participant r);

	void updateParticipant(Participant r);
	
	void deleteParticipant(Integer roleId);

	Participant findById(Integer id);
	
	List<Participant> getList(Criterion...criterion);
	List<Participant> getList(Session sess, Criterion...criterion);
	int getCount(Criterion...criterion);

	// <T> List<T> getList(Class<T> entityClass, Map<String, Object> parameters,
	// Criterion... criterion) throws Exception;
	//
	// <T> int getCount(Class<T> entityClass, Map<String, Object> parameters,
	// Criterion... criterion) throws Exception;
}
