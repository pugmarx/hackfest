/**
 * 
 */
package com.krishagni.luceneqp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

@Entity(name = "CATISSUE_PARTICIPANT")
@Indexed
public class Participant {

	@Id
	@DocumentId
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IDENTIFIER", unique = true, nullable = false)
	private Integer id;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "MIDDLE_NAME")
	private String middleName;

	@Column(name = "GENDER", length = 20)
	private String gender;

	@Column(name = "BIRTH_DATE")
	private Date birthDate;

	@Column(name = "ACTIVITY_STATUS")
	private String activityStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}

}
