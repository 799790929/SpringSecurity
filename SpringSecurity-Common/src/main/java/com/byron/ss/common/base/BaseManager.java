package com.byron.ss.common.base;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
/**
 * @author byron
 */
@Transactional
public abstract class BaseManager <E,PK extends Serializable>{
	
	protected Log log = LogFactory.getLog(getClass());

	protected abstract EntityDao getEntityDao();

	@Transactional(readOnly=true)
	public E getById(PK id) throws DataAccessException{
		return (E)getEntityDao().getById(id);
	}
	
	@Transactional(readOnly=true)
	public List<E> findAll() throws DataAccessException{
		return getEntityDao().findAll();
	}
	
	/** 根据id检查是否插入或是更新数据 */
	public void saveOrUpdate(E entity) throws DataAccessException{
		getEntityDao().saveOrUpdate(entity);
	}
	
	/** 插入数据 */
	public void save(E entity) throws DataAccessException{
		getEntityDao().save(entity);
	}
	
	public void removeById(PK id) throws DataAccessException{
		getEntityDao().deleteById(id);
	}
	
	public void update(E entity) throws DataAccessException{
		getEntityDao().update(entity);
	}
	
	@Transactional(readOnly=true)
	public boolean isUnique(E entity, String uniquePropertyNames) throws DataAccessException {
		return getEntityDao().isUnique(entity, uniquePropertyNames);
	}
	
	
	
	
	
	
	
	/*public List<E> findBy(final String propertyName,
			final Object fieldValue, String sql){
		return getEntityDao().findBy(propertyName, fieldValue, sql);
	}
	
	public List<E> findBy(final String[] fieldNames,
			final Object[] fieldValues, String sql) {
		return getEntityDao().findBy(fieldNames, fieldValues, sql);
	}
	
	public List<E> executeFind(final String query, final Object[] values) {
		return getEntityDao().executeFind(query, values);
	}*/
	
}
