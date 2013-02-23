package com.krishagni.hackfest.dao;

import com.krishagni.hackfest.entities.Participant;

public interface ParticipantDAO {

	void saveParticipant(Participant r);

	void updateParticipant(Participant r);
	
	void deleteParticipant(Integer roleId);
	
	Participant findById(Integer id);
}
