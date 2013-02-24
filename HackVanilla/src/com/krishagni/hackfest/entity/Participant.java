/**
 * 
 */
package com.krishagni.hackfest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.jasypt.hibernate4.type.EncryptedStringType;

@TypeDef(name = "encryptedString", typeClass = EncryptedStringType.class, parameters = { @Parameter(name = "encryptorRegisteredName", value = "strongHibernateStringEncryptor") })
// @DynamicUpdate
@Entity(name = "PARTICIPANT")
// @Table(appliesTo = "participant")
public class Participant {

	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "participant_seq", sequenceName = "participant_seq", allocationSize = 1)
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(generator = "participant_seq")
	@Column(unique = true, nullable = false)
	private Integer id;

	//@Type(type = "encryptedString")
	private String name;

	@Column(length = 1)
	//@Type(type = "encryptedString")
	private String gender;

	//@Type(type = "encryptedString")
	private String street;
	
	//@Type(type = "encryptedString")
	private String city;
	
	//@Type(type = "encryptedString")
	private String state;

	// @Type(type = "encryptedString")
	private String country;

	//@Type(type = "encryptedString")
	private String phone;
	
	//@Type(type = "encryptedString")
	private String zip;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

}
