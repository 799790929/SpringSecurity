/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.byron.ss.dao;

import org.springframework.stereotype.Repository;

import com.byron.ss.common.base.BaseHibernateDao;
import com.byron.ss.model.Groups;

@Repository
public class GroupsDao extends BaseHibernateDao<Groups,java.lang.String>{

	public Class getEntityClass() {
		return Groups.class;
	}
	
	/*public Page findPage(GroupsQuery query) {
        
		String sql = "select t from Groups t where 1=1 "
			  	+ "/~ and t.enable = {enable} ~/"
			  	+ "/~ and t.name = {name} ~/"
				+ "/~ order by [sortColumns] ~/";

        StringBuilder sql2 = new StringBuilder("select t from Groups t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql2.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getEnable())) {
            sql2.append(" and  t.enable = :enable ");
        }
        if(isNotEmpty(query.getName())) {
            sql2.append(" and  t.name = :name ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql2.append(" order by :sortColumns ");
        }	
        
		return pageQuery(sql,query);
	}*/
	

}
