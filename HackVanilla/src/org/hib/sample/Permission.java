package org.hib.sample;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "PERMISSION")
@SequenceGenerator(name = "SEQ_GEN", sequenceName = "PERMISSION_SEQ", allocationSize = 1)
@Audited
public class Permission implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int permissionId;

	private String permissionName;

	private Role role;

	public Permission() {
	}

	public Permission(int roleId, String roleName) {
		super();
		this.permissionId = roleId;
		this.permissionName = roleName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_GEN")
	@Column(name = "PERMISSIONID", unique = true, nullable = false)
	public int getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(int roleId) {
		this.permissionId = roleId;
	}

	@Column(name = "PERMISSIONNAME", unique = false, nullable = false)
	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String roleName) {
		this.permissionName = roleName;
	}

	@ManyToOne
	@JoinColumn(name = "ROLEID")
	public Role getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

}
