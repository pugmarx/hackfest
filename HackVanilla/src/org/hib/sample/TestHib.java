package org.hib.sample;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class TestHib {

	public static void retrieveRoles() {
		System.out.println("Retrieving Roles...");
		Session session = HibUtil.getSessionFactory().openSession();
		List<Role> roles = session.createQuery("from Role").list();
		for (Role role : roles) {
			System.out.println(role.getRoleName() + "\t " + role.getId());
		}
		session.close();
	}

	// public static void saveRole(String title) {
	// Session session = HibUtil.getSessionFactory().openSession();
	// Transaction tx = session.beginTransaction();
	// System.out.println("Session is connected: " + session.isConnected());
	// Role role = new Role();
	// role.setRoleName(title);
	// // user.setDate(new Date());
	// System.out.println("\n Saving role " + role.getRoleName());
	// session.save(role);
	// session.flush();
	// session.clear();
	// tx.commit();
	// session.close();
	// }

	public static Integer saveRole(String title) {
		RoleDAO dao = new RoleDAOImpl();
		Role r = getRandomRole();
//		r.setRoleName(title);
//		Set<Permission> mappedP = new HashSet<Permission>();
//		for (int i = 0; i < 2; i++) {
//			Permission p = getRandomPermission();
//			p.setRole(r);
//			mappedP.add(p);
//		}
//		r.setMappedPermissions(mappedP);
		// Role role = new Role();
		// role.setRoleName(title);
		dao.saveRole(r);
		return r.getId();
	}

	public static void updateRole(Integer roleId, String roleName) {
		RoleDAO dao = new RoleDAOImpl();
		Role r = dao.findById(roleId);
		r.setRoleName(roleName);
		// TODO check whether onFlushDirty is called with saveOrUpdate?
		dao.updateRole(r);

	}

	public static void deleteRole(Integer roleId) {
		RoleDAO dao = new RoleDAOImpl();
		// r.setRoleName(roleName);
		// TODO check whether onFlushDirty is called with saveOrUpdate?
		dao.deleteRole(roleId);
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

	private static Role getRandomRole() {
		Role r = new Role();
		r.setRoleName(UUID.randomUUID().toString());
		return r;
	}

	private static Permission getRandomPermission() {
		Permission p = new Permission();
		p.setPermissionName(UUID.randomUUID().toString());
		return p;
	}

	public static void main(String args[]) {
		Integer id = saveRole("abc");
		System.out.println(id);
		updateRole(id, "def");
		deleteRole(id);
		// saveRole("abc1");
		// saveRole("def");
		// saveRole("Hi");
		// saveRole("hello");
		// retrieveRoles();
		// savePermission("p1");
		// savePermission("p3");
	}
}
