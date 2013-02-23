package org.hib.sample;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.Session;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.hibernate.envers.query.criteria.AuditCriterion;

/**
 * Provides operations to access audit data
 * 
 * @author syed_rizvi
 * 
 * @param <T>
 */
public class AuditDataManagerImpl<T> implements AuditDataManager<T> {

	private Class<T> entityClass;

	private AuditReader getAuditReader(Session session) {
		return AuditReaderFactory.get(session);
	}

	public AuditDataManagerImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hib.sample.AuditDataManager#getEntityAtPreviousRevision(java.io.
	 * Serializable, int)
	 */
	@Override
	public T getEntityAtPreviousRevision(Serializable entityId, int current_rev) throws GRAuditException {
		Session session = null;
		try {
			// TODO put classtype validation
			session = HibUtil.getSessionFactory().openSession();
			AuditReader auditReader = getAuditReader(session);
			Number previousRevision = (Number) auditReader.createQuery().forRevisionsOfEntity(entityClass, false, true)
					.addProjection(AuditEntity.revisionNumber().max()).add(AuditEntity.id().eq(entityId))
					.add(AuditEntity.revisionNumber().lt(current_rev)).getSingleResult();

			if (previousRevision != null)
				return (T) auditReader.find(entityClass, entityId, previousRevision);
			else
				return null;
		} catch (Exception e) {
			// TODO log.error;
			throw new GRAuditException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hib.sample.AuditDataManager#getCurrentRevisionNumber()
	 */
	@Override
	public Integer getCurrentRevisionNumber() throws GRAuditException {
		Session session = null;
		try {
			session = HibUtil.getSessionFactory().openSession();
			AuditReader auditReader = getAuditReader(session);
			Number currentRevision = (Number) auditReader.createQuery().forRevisionsOfEntity(entityClass, false, true)
					.addProjection(AuditEntity.revisionNumber().max()).getSingleResult();
			if (currentRevision != null)
				return currentRevision.intValue();
			else
				return null;
		} catch (Exception e) {
			// TODO log.error;
			throw new GRAuditException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	// FIXME
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.hib.sample.AuditDataManager#getRevisionNumberWithCondition(java.util
	 * .Map)
	 */
	@Override
	public Integer getRevisionNumberWithCondition(Map<String, Object> conditionMap) throws GRAuditException {
		Session session = null;
		try {
			session = HibUtil.getSessionFactory().openSession();
			AuditReader auditReader = getAuditReader(session);
			AuditQuery query = auditReader.createQuery().forRevisionsOfEntity(entityClass, false, true);
			for (Map.Entry<String, Object> entry : conditionMap.entrySet()) {
				query.add(AuditEntity.property(entry.getKey()).eq(entry.getValue()));
			}
			// GRRevEntity revEntity = (GRRevEntity)query.getSingleResult();
			Object revEntity = query.getSingleResult();
			return null;
		} catch (Exception e) {
			// TODO log.error;
			throw new GRAuditException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hib.sample.AuditDataManager#getRevisionsByUser(java.lang.String)
	 */
	@Override
	public List<T> getRevisionsByUser(String userLoginId) throws GRAuditException {
		List<T> list = null;
		Session session = null;
		try {
			session = HibUtil.getSessionFactory().openSession();
			AuditReader auditReader = getAuditReader(session);
			AuditQuery query = auditReader.createQuery().forRevisionsOfEntity(entityClass, true, true);
			query.add(AuditEntity.revisionProperty("actor").eq(userLoginId));
			list = query.getResultList();
		} catch (Exception e) {
			// TODO log.error;
			throw new GRAuditException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	/**
	 * Base getHistory implementation -- other operations can be simple
	 * decorators -- adding criteria as per the requirements
	 * 
	 * @param criteria
	 * @return
	 * @throws GRAuditException
	 */
	private List<?> getHistory(AuditCriterion... criteria) throws GRAuditException {
		List<?> list = null;
		Session session = null;
		try {
			session = HibUtil.getSessionFactory().openSession();
			AuditReader auditReader = getAuditReader(session);
			AuditQuery query = auditReader.createQuery().forRevisionsOfEntity(entityClass, false, true);
			for (AuditCriterion criterion : criteria) {
				query.add(criterion);
			}
			list = query.getResultList();
		} catch (Exception e) {
			// TODO log.error;
			throw new GRAuditException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hib.sample.AuditDataManager#getEntityHistory(java.lang.String)
	 */
	@Override
	public Map<String, T> getEntityHistory(String userLoginId) throws GRAuditException {
		Map<String, T> revHistory = new TreeMap<String, T>();
		try {
			AuditCriterion criterion = AuditEntity.revisionProperty("actor").eq(userLoginId);
			List<?> list = getHistory(criterion);
			revHistory = generateRevMapFromList(list);
		} catch (Exception e) {
			// TODO log.error;
			throw new GRAuditException(e);
		}
		return revHistory;
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// * org.hib.sample.AuditDataManager#getEntityHistory(java.io.Serializable)
	// */
	// @Override
	// public Map<Integer, Map<String, T>> getEntityHistory(Serializable
	// entityId) throws GRAuditException {
	// Map<Integer, Map<String, T>> revHistory = new TreeMap<Integer,
	// Map<String, T>>();
	// try {
	// AuditCriterion criterion = AuditEntity.id().eq(entityId);
	// List<?> list = getHistory(criterion);
	// for (Object o : list) {
	// Object[] objectArray = (Object[]) o;
	// GRRevEntity revEntity = (GRRevEntity) objectArray[1];
	// Integer revNumber = revEntity.getId();
	// Map<String, T> innerMap = null;
	// if (revHistory.isEmpty() || revHistory.get(revNumber) == null) {
	// innerMap = new TreeMap<String, T>();
	// } else {
	// innerMap = revHistory.get(revNumber);
	// }
	// innerMap.put(objectArray[2].toString(), (T) objectArray[0]);
	// revHistory.put(revNumber, innerMap);
	// }
	// } catch (Exception e) {
	// // TODO log.error;
	// throw new GRAuditException(e);
	// }
	// return revHistory;
	// }

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
	@Override
	public Map<Integer, AuditData<T>> getEntityHistory(Integer entityId) throws GRAuditException {
		Map<Integer, AuditData<T>> revHistory = new TreeMap<Integer, AuditData<T>>();
		try {
			AuditCriterion criterion = AuditEntity.id().eq(entityId);
			List<?> list = getHistory(criterion);
			for (Object o : list) {
				Object[] objectArray = (Object[]) o;
				GRRevEntity revEntity = (GRRevEntity) objectArray[1];
				Integer revNumber = revEntity.getId();
				T entity = (T) objectArray[0];
				AuditData<T> auditData = new AuditData<T>();
				auditData.setEntity(entity);
				auditData.setRevisionDate(revEntity.getRevisionDate());
				auditData.setRevisionNumber(revNumber);
				auditData.setUserLoginId(revEntity.getActor());
				auditData.setRevisionType(objectArray[2].toString());
				// TODO auditData.setUserId(?)
				revHistory.put(revNumber, auditData);
			}
		} catch (Exception e) {
			// TODO log.error;
			throw new GRAuditException(e);
		}
		return revHistory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.hib.sample.AuditDataManager#getEntityHistory(java.io.Serializable,
	 * java.lang.String)
	 */
	@Override
	public Map<String, T> getEntityHistory(Serializable entityId, String userLoginId) throws GRAuditException {
		Map<String, T> revHistory = new TreeMap<String, T>();
		try {
			AuditCriterion idCriterion = AuditEntity.id().eq(entityId);
			AuditCriterion actorCriterion = AuditEntity.revisionProperty("actor").eq(userLoginId);
			List<?> list = getHistory(idCriterion, actorCriterion);
			revHistory = generateRevMapFromList(list);
		} catch (Exception e) {
			// TODO log.error;
			throw new GRAuditException(e);
		}
		return revHistory;
	}

	/**
	 * The original 'list' obtained from AuditReader is iterated and the
	 * Object[] array is interpreted.
	 * 
	 * @see http://docs.jboss.org/envers/docs/index.html#revisions-of-entity <br>
	 * <br>
	 *      <strong>Excerpt:</strong> The result will be a list of three element
	 *      arrays. The first element will be the changed entity instance. The
	 *      second will be an entity containing revision data (if no custom
	 *      entity is used, this will be an instance of DefaultRevisionEntity).
	 *      The third will be the type of the revision (one of the values of the
	 *      RevisionType enumeration: ADD, MOD, DEL)
	 */
	private Map<String, T> generateRevMapFromList(List<?> list) {
		Map<String, T> revHistory = new TreeMap<String, T>();
		for (Object o : list) {
			Object[] objectArray = (Object[]) o;
			if (objectArray.length > 1) {
				revHistory.put(objectArray[2].toString(), (T) objectArray[0]);
			}
		}
		return revHistory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.hib.sample.AuditDataManager#getEntityAtRevision(java.lang.Integer)
	 */
	@Override
	public T getEntityAtRevision(Integer revisionNumber) throws GRAuditException {
		Session session = null;
		T entity = null;
		try {
			// TODO validate revisionNumber > 0
			session = HibUtil.getSessionFactory().openSession();
			AuditReader auditReader = getAuditReader(session);
			entity = (T) auditReader.findRevision(entityClass, revisionNumber);
		} catch (Exception e) {
			// TODO log.error;
			throw new GRAuditException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return entity;
	}
}
