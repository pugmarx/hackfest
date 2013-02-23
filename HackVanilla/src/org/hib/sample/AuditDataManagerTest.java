package org.hib.sample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class AuditDataManagerTest {

	Session session;
	AuditDataManager<Role> admRole;

	@Before
	public void startUp() {
		session = HibUtil.getSessionFactory().openSession();
		admRole = new AuditDataManagerImpl<Role>(Role.class);
	}

	@Test
	public void testGetCurrentVersion() throws Exception {
		clearRoleAuditLogs();
		Role r = new Role();
		r.setRoleName("firstVersion");
		RoleDAO roleDAO = new RoleDAOImpl();
		roleDAO.saveRole(r);

		Integer currentVersion = admRole.getCurrentRevisionNumber();
		System.out.println("Current version is:" + currentVersion);
		assertNotNull(currentVersion);
		assertTrue(currentVersion > 0);
	}

	@Test
	public void testGetPreviousVersion() throws Exception {
		clearRoleAuditLogs();
		Role r = new Role();
		r.setRoleName("firstVersion");
		RoleDAO roleDAO = new RoleDAOImpl();
		roleDAO.saveRole(r);

		r.setRoleName("secondVersion");
		roleDAO.updateRole(r);

		Integer currentVersion = admRole.getCurrentRevisionNumber();
		Role fetchedRole = (Role) admRole.getEntityAtPreviousRevision(r.getId(), currentVersion);
		assertEquals("firstVersion", fetchedRole.getRoleName());
	}

	@Ignore
	@Test
	public void testGetVersionWithCondition() throws GRAuditException {
		clearRoleAuditLogs();
		Role r = new Role();
		r.setRoleName("firstVersion");
		RoleDAO roleDAO = new RoleDAOImpl();
		roleDAO.saveRole(r);

		r.setRoleName("secondVersion");
		roleDAO.updateRole(r);

		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("roleName", "secondVersion");
		admRole.getRevisionNumberWithCondition(conditions);
		// Integer currentVersion = auditDAO.getCurrentRevisionNumber(r);
		// Role fetchedRole = (Role) auditDAO.getEntityAtPreviousRevision(r,
		// currentVersion);
		// assertEquals("firstVersion", fetchedRole.getRoleName());
	}

	@Test
	public void testGetRevisionsByUser() throws GRAuditException {
		clearRoleAuditLogs();
		Role r = new Role();
		r.setRoleName("firstVersion");
		RoleDAO roleDAO = new RoleDAOImpl();
		roleDAO.saveRole(r);

		r.setRoleName("secondVersion");
		roleDAO.updateRole(r);

		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("roleName", "secondVersion");
		List<Role> list = admRole.getRevisionsByUser("Bob");

		assertEquals("secondVersion", ((Role) list.get(1)).getRoleName());
	}

	@Test
	public void testGetEntityHistoryByUser() throws GRAuditException {
		clearRoleAuditLogs();
		Role r = new Role();
		r.setRoleName("firstVersion");
		RoleDAO roleDAO = new RoleDAOImpl();
		roleDAO.saveRole(r);

		r.setRoleName("secondVersion");
		roleDAO.updateRole(r);

		roleDAO.deleteRole(r.getId());

		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("roleName", "secondVersion");
		Map<String, Role> revHistory = admRole.getEntityHistory("user@test.com");

		for (Map.Entry<String, Role> entry : revHistory.entrySet()) {
			System.out.print(entry.getKey());
			System.out.print("->");
			System.out.println(entry.getValue());
			// System.out.println(entry.getValue().getRoleName());
		}
	}

	@Test
	public void testGetEntityHistoryById() throws GRAuditException {
		clearRoleAuditLogs();
		Role r = new Role();
		r.setRoleName("firstVersion");
		RoleDAO roleDAO = new RoleDAOImpl();
		roleDAO.saveRole(r);
		r.setRoleName("secondVersion");
		roleDAO.updateRole(r);
		roleDAO.deleteRole(r.getId());

		Map<Integer, AuditData<Role>> revHistory = admRole.getEntityHistory(r.getId());
		for (Map.Entry<Integer, AuditData<Role>> entry : revHistory.entrySet()) {
			System.out.print(entry.getKey());
			System.out.print("->");
			System.out.print(entry.getValue().getRevisionType());
			System.out.print("->");
			System.out.print(entry.getValue().getUserLoginId());
			System.out.print("->");
			System.out.println(entry.getValue().getEntity());
		}
	}

	private void clearRoleAuditLogs() {
		Transaction t = session.beginTransaction();
		Query truncateA = session.createSQLQuery("delete from ROLE_AUD");
		Query truncateB = session.createQuery("delete from Role");
		Query truncateC = session.createSQLQuery("delete from GRREVINFO");
		truncateA.executeUpdate();
		truncateB.executeUpdate();
		truncateC.executeUpdate();
		t.commit();
	}

	@After
	public void tearDown() {
		if (session != null && session.isOpen()) {
			session.clear();
			session.flush();
			session.close();
		}
	}
}
