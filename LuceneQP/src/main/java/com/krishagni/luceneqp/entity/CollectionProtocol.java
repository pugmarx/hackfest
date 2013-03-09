/**
 * 
 */
package com.krishagni.luceneqp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

@Entity(name = "CATISSUE_COLLECTION_PROTOCOL")
@Indexed
public class CollectionProtocol {

	@Id
	@DocumentId
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IDENTIFIER", unique = true, nullable = false)
	private Integer id;

	@Column(name = "PARENT_CP_ID")
	private Integer parentCpId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentCpId() {
		return parentCpId;
	}

	public void setParentCpId(Integer parentCpId) {
		this.parentCpId = parentCpId;
	}

}
