package org.hib.sample;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.jasypt.hibernate.type.EncryptedStringType;

@TypeDef(name = "encryptedString", typeClass = EncryptedStringType.class, parameters = 
{ @Parameter(name = "encryptorRegisteredName", value = "strongHibernateStringEncryptor") })
@Entity
@Table(name = "ROLE")
@SequenceGenerator(name = "SEQ_GEN", sequenceName = "ROLE_SEQ", allocationSize = 1)
@Audited
// @AttributeOverride(name = "id", column = @Column(name = "ID"))
public class Role extends BaseEntity implements java.io.Serializable, Auditable {

	private static final long serialVersionUID = -6199760310110308798L;

	private Integer id;

	@NotAudited
	private String roleName;

	private Set<Permission> mappedPermissions;

	public Role() {
	}

	public Role(int roleId, String roleName) {
		super();
		this.id = roleId;
		this.roleName = roleName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_GEN")
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer roleId) {
		this.id = roleId;
	}

	@Column(name = "ROLENAME", unique = false, nullable = false, length = 254)
	@Type(type = "encryptedString")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "role", fetch = FetchType.EAGER)
	public Set<Permission> getMappedPermissions() {
		return mappedPermissions;
	}

	/**
	 * @param mappedPermissions
	 *            the mappedPermissions to set
	 */
	public void setMappedPermissions(Set<Permission> mappedPermissions) {
		this.mappedPermissions = mappedPermissions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Role [roleId=" + id + ", roleName=" + roleName + ",createdBy=" + createdBy + ", createdOn=" + createdOn
				+ ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn + "]";
	}

}
