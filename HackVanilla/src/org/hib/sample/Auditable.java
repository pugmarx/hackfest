package org.hib.sample;

/**
 * Interface that all entities should extend for audit-based fetching
 * 
 * @author syed_rizvi
 * 
 */
public interface Auditable {

	public void setId(Integer id);

	public Integer getId();

}
