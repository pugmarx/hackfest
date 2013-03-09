package com.krishagni.luceneqp.util;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

import com.krishagni.luceneqp.entity.CollectionProtocol;
import com.krishagni.luceneqp.entity.CollectionProtocolReg;
import com.krishagni.luceneqp.entity.Participant;

public class DataGenerator {
	private final static Date[] dates = { new Date("01-Jan-2010"),
			new Date("12-Feb-2011"), new Date("20-Mar-2012"),
			new Date("17-Oct-2013"), new Date("01-Jan-2010") };
	private final static String firstNames[] = { "Alex", "Beth", "Cathy",
			"Derrick", "Eva", "Fin", "Garp", "Harry", "Ivan" };
	private final static String lastNames[] = { "William", "Knowles", "Crowe",
			"Fox", "Ford", "Sammy", "Gray", "Smith", "Hegel" };

	public static Participant getParticipant() {
		Participant p = new Participant();
		p.setActivityStatus(UUID.randomUUID().toString());
		p.setBirthDate(dates[new Random().nextInt(4)]);
		p.setFirstName(firstNames[new Random().nextInt(8)]);
		p.setLastName(lastNames[new Random().nextInt(8)]);
		// p.setMiddleName(null);
		return p;
	}

	public static CollectionProtocol getCollectionProtocol() {
		CollectionProtocol cp = new CollectionProtocol();
		return cp;
	};

	public static CollectionProtocolReg getCollectionProtocolReg() {
		CollectionProtocolReg cpr = new CollectionProtocolReg();
		cpr.setParticipantId(getParticipant());
		cpr.setCollectionProtocol(getCollectionProtocol());
		cpr.setRegistrationDate(dates[new Random().nextInt(4)]);
		return cpr;
	};
}
