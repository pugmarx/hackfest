package com.krishagni.hackfest.util;

import java.util.List;
import java.util.UUID;

import org.hib.sample.Permission;
import org.hibernate.Session;

import com.krishagni.hackfest.dao.ParticipantDAO;
import com.krishagni.hackfest.dao.ParticipantDAOImpl;
import com.krishagni.hackfest.entities.Participant;

public class TestHib {

	public static void retrieveParticipants() {
		System.out.println("Retrieving Participants...");
		Session session = HibUtil.getSessionFactory().openSession();
		List<Participant> roles = session.createQuery("from Participant")
				.list();
		for (Participant role : roles) {
			System.out.println(role.getName() + "\t " + role.getId());
		}
		session.close();
	}

	// public static void saveParticipant(String title) {
	// Session session = HibUtil.getSessionFactory().openSession();
	// Transaction tx = session.beginTransaction();
	// System.out.println("Session is connected: " + session.isConnected());
	// Participant role = new Participant();
	// role.setParticipantName(title);
	// // user.setDate(new Date());
	// System.out.println("\n Saving role " + role.getParticipantName());
	// session.save(role);
	// session.flush();
	// session.clear();
	// tx.commit();
	// session.close();
	// }

	public static Integer saveParticipant(String title) {
		ParticipantDAO dao = new ParticipantDAOImpl();
		Participant r = getRandomParticipant();
		// r.setParticipantName(title);
		// Set<Permission> mappedP = new HashSet<Permission>();
		// for (int i = 0; i < 2; i++) {
		// Permission p = getRandomPermission();
		// p.setParticipant(r);
		// mappedP.add(p);
		// }
		// r.setMappedPermissions(mappedP);
		// Participant role = new Participant();
		// role.setParticipantName(title);
		dao.saveParticipant(r);
		return r.getId();
	}

	public static void updateParticipant(Integer roleId, String roleName) {
		ParticipantDAO dao = new ParticipantDAOImpl();
		Participant r = dao.findById(roleId);
		r.setName(roleName);
		// TODO check whether onFlushDirty is called with saveOrUpdate?
		dao.updateParticipant(r);

	}

	public static void deleteParticipant(Integer roleId) {
		ParticipantDAO dao = new ParticipantDAOImpl();
		// r.setParticipantName(roleName);
		// TODO check whether onFlushDirty is called with saveOrUpdate?
		dao.deleteParticipant(roleId);
	}

	public static void savePermission(String title) {
		Session session = HibUtil.getSessionFactory().openSession();
		System.out.println("Session is connected: " + session.isConnected());
		Permission perm = new Permission();
		perm.setPermissionName(title);
		// user.setDate(new Date());
		System.out.println("\n Saving permission " + perm.getPermissionName());
		session.save(perm);
		session.flush();
		session.close();
	}

	private static Participant getRandomParticipant() {
		Participant r = new Participant();
		r.setName(UUID.randomUUID().toString());
		r.setCity(UUID.randomUUID().toString());
		r.setGender("F");
		r.setState(UUID.randomUUID().toString());
		r.setState(UUID.randomUUID().toString());
		r.setCountry(UUID.randomUUID().toString());
		r.setZip(UUID.randomUUID().toString());
		return r;
	}

	private static Permission getRandomPermission() {
		Permission p = new Permission();
		p.setPermissionName(UUID.randomUUID().toString());
		return p;
	}

	public static void main(String args[]) {
		Integer id = saveParticipant("abc");
		System.out.println(id);
		//updateParticipant(id, "def");
		//deleteParticipant(id);
	}
}
