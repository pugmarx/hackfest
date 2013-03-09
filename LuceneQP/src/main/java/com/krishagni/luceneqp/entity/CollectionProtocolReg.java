/**
 * 
 */
package com.krishagni.luceneqp.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

@Entity(name = "CATISSUE_COLL_PROT_REG")
@Indexed
public class CollectionProtocolReg {

	@Id
	@DocumentId
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IDENTIFIER", unique = true, nullable = false)
	private Integer id;

	@Column(name = "REGISTRATION_DATE")
	private Date registrationDate;

	//@Column(name = "COLLECTION_PROTOCOL_ID")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="COLLECTION_PROTOCOL_ID")
	private CollectionProtocol collectionProtocol;

	//@Column(name = "PARTICIPANT_ID")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="PARTICIPANT_ID")
	private Participant participantId;

	@Column(name = "ACTIVITY_STATUS")
	private String activityStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public CollectionProtocol getCollectionProtocol() {
		return collectionProtocol;
	}

	public void setCollectionProtocol(CollectionProtocol collectionProtocol) {
		this.collectionProtocol = collectionProtocol;
	}

	public Participant getParticipantId() {
		return participantId;
	}

	public void setParticipantId(Participant participantId) {
		this.participantId = participantId;
	}

	public String getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}
}
