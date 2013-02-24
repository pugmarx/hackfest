package com.krishagni.hackfest.util;

import java.util.UUID;

import com.krishagni.hackfest.entity.Participant;

public class DataGenerator {
	public static Participant getRandomParticipant() {
		Participant r = new Participant();
		r.setName(UUID.randomUUID().toString());
		r.setCity(UUID.randomUUID().toString());
		r.setGender("F");
		r.setState(UUID.randomUUID().toString());
		//r.setCountry(UUID.randomUUID().toString());
		r.setCountry("India");
		r.setZip(UUID.randomUUID().toString());
		r.setPhone(UUID.randomUUID().toString());
		return r;
	}
}
