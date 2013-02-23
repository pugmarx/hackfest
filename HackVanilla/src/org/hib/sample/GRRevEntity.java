package org.hib.sample;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

@Entity
@RevisionEntity(GREntityAuditListener.class)
@Table(name = "GRREVINFO")
@SequenceGenerator(name = "SEQ_GEN", sequenceName = "GR_REVINFO_SEQ", allocationSize = 1)
public class GRRevEntity /* extends DefaultRevisionEntity */{
	private static final long serialVersionUID = 8419389165951325828L;
	private String actor;

	@Id
	@GeneratedValue(generator = "SEQ_GEN")
	@RevisionNumber
	private int id;

	@RevisionTimestamp
	@Column(name="TSTAMP")
	private long timestamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Transient
	public Date getRevisionDate() {
		return new Date(timestamp);
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof DefaultRevisionEntity))
			return false;

		GRRevEntity that = (GRRevEntity) o;

		if (id != that.id)
			return false;
		if (timestamp != that.timestamp)
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = id;
		result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
		return result;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String username) {
		this.actor = username;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GRRevEntity [actor=" + actor + ", id=" + id + ", timestamp=" + timestamp + "]";
	}

}