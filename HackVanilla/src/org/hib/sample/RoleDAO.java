package org.hib.sample;

public interface RoleDAO {

	void saveRole(Role r);

	void updateRole(Role r);
	
	void deleteRole(Integer roleId);
	
	Role findById(Integer id);
}
