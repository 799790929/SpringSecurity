package com.byron.ss.common.base;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
/**
 * @author byron
 */
public interface EntityDao <E,PK extends Serializable>{

	public Object getById(PK id) throws DataAccessException;
	
	public void deleteById(PK id) throws DataAccessException;
	
	/** 插入数据 */
	public void save(E entity) throws DataAccessException;
	
	/** 更新数据 */
	public void update(E entity) throws DataAccessException;

	/** 根据id检查是否插入或是更新数据 */
	public void saveOrUpdate(E entity) throws DataAccessException;

	public boolean isUnique(E entity, String uniquePropertyNames) throws DataAccessException;
	
	/** 用于hibernate.flush() 有些dao实现不需要实现此类  */
	public void flush() throws DataAccessException;
	
	public List<E> findAll() throws DataAccessException;
	
	
	
	
	
	
	public List<E> findBy(final String propertyName, final Object fieldValue, String sql);
	/*public List<E> findBy(final String[] fieldNames,final Object[] fieldValues, String sql);
	
	public List<E> executeFind(final String query, final Object[] values);*/
	
	public List<E> queryByPage(int start, int count, String query);
	
	public long getRows(String query);
	
	public List<E> findBy(final String[] fieldNames,
			final Object[] fieldValues, String sql);
	
	public List<E> executeFind(final String query, final Object[] values);
	
	public List<Object[]> getFieldsBySql(final String query);
	
	public List<E> findAllBy(final String propertyName, final Object value);
	
	public long getRows(String query, final Object[] values);
	
	public List<E> queryByPage(int start, int count, String query, final Object[] values);
	
}
