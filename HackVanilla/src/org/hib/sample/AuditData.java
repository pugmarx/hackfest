package org.hib.sample;

import java.util.Date;

/**
 * Bean to hold audit information
 * 
 * @author syed_rizvi
 * 
 */
public class AuditData<T> {
	private T entity;
	private Integer revisionNumber;
	private Integer userId;
	private String userLoginId;
	private Date revisionDate;
	private String revisionType;

	/**
	 * @return the entity
	 */
	public T getEntity() {
		return entity;
	}

	/**
	 * @param entity
	 *            the entity to set
	 */
	public void setEntity(T entity) {
		this.entity = entity;
	}

	/**
	 * @return the revisionNumber
	 */
	public Integer getRevisionNumber() {
		return revisionNumber;
	}

	/**
	 * @param revisionNumber
	 *            the revisionNumber to set
	 */
	public void setRevisionNumber(Integer revisionNumber) {
		this.revisionNumber = revisionNumber;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the userLoginId
	 */
	public String getUserLoginId() {
		return userLoginId;
	}

	/**
	 * @param userLoginId
	 *            the userLoginId to set
	 */
	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}

	/**
	 * @return the revisionDate
	 */
	public Date getRevisionDate() {
		return revisionDate;
	}

	/**
	 * @param revisionDate
	 *            the revisionDate to set
	 */
	public void setRevisionDate(Date revisionDate) {
		this.revisionDate = revisionDate;
	}

	/**
	 * @return the revisionType
	 */
	public String getRevisionType() {
		return revisionType;
	}

	/**
	 * @param revisionType
	 *            the revisionType to set
	 */
	public void setRevisionType(String revisionType) {
		this.revisionType = revisionType;
	}
}
