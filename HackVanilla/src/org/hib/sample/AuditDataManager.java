package org.hib.sample;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface AuditDataManager<T> {

	public abstract T getEntityAtPreviousRevision(Serializable entityId, int current_rev) throws GRAuditException;

	public abstract Integer getCurrentRevisionNumber() throws GRAuditException;

	// FIXME
	public abstract Integer getRevisionNumberWithCondition(Map<String, Object> conditionMap) throws GRAuditException;

	public abstract List<T> getRevisionsByUser(String userLoginId) throws GRAuditException;

	/**
	 * Returns a map with:
	 * 
	 * <pre>
	 * RevType [ADD|MOD|DEL] -> Entity [T]
	 * </pre>
	 * 
	 * @param userLoginId
	 * @return
	 * @throws GRAuditException
	 */
	public abstract Map<String, T> getEntityHistory(String userLoginId) throws GRAuditException;

	// /**
	// * Returns a map with:
	// *
	// * <pre>
	// * revNumber -> AuditData
	// * </pre>
	// *
	// * @param userLoginId
	// * @return
	// * @throws GRAuditException
	// */
	// public abstract Map<Integer, Map<String, T>>
	// getEntityHistory(Serializable entityId) throws GRAuditException;

	/**
	 * Returns a map with:
	 * 
	 * <pre>
	 * RevType [ADD|MOD|DEL] -> Entity [T]
	 * </pre>
	 * 
	 * @param entityId
	 * 
	 * @param userLoginId
	 * @return
	 * @throws GRAuditException
	 */
	public abstract Map<String, T> getEntityHistory(Serializable entityId, String userLoginId) throws GRAuditException;

	public abstract T getEntityAtRevision(Integer revisionNumber) throws GRAuditException;

	/**
	 * Returns a map with:
	 * 
	 * <pre>
	 * revNumber -> AuditData
	 * </pre>
	 * 
	 * @param userLoginId
	 * @return
	 * @throws GRAuditException
	 */
	public abstract Map<Integer, AuditData<T>> getEntityHistory(Integer entityId) throws GRAuditException;

}