/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.byron.ss.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

import com.byron.ss.common.base.BaseHibernateDao;
import com.byron.ss.common.util.HibernateToolsUtil;
import com.byron.ss.model.Resources;
import com.byron.ss.model.Roles;
import com.byron.ss.model.RolesResources;

@Repository
public class RolesResourcesDao extends BaseHibernateDao<RolesResources,java.lang.String>{

	public Class getEntityClass() {
		return RolesResources.class;
	}
	
	public boolean hasResourceByRole(Roles role) throws Exception {
		boolean exists = false;
		Session session = this.getSessionFactory().openSession();
		
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT {rs.*} ");
		hql.append("FROM " + HibernateToolsUtil.getTableName(Resources.class) + " rs ");
		hql.append("," + HibernateToolsUtil.getTableName(RolesResources.class) + " rrs ");
		hql.append("," + HibernateToolsUtil.getTableName(Roles.class) + " r ");
		hql.append(" where 1=1 and rs."+ Resources.rs_pk_resource +"=rrs.resource_id and r."+ Roles.r_pk_role +"=rrs.role_id ");
		if(null == role) {
			throw new Exception("角色不能为空!");
		}
		hql.append(" and r."+ Roles.r_pk_role +"='" + role.getId() + "' ");
		logger.info("\nsql->[" + hql.toString() + "]");
		SQLQuery query = session.createSQLQuery(hql.toString());
		query.addEntity("rs", Resources.class);
		List<Object> list = query.list();
		if(null != list && list.size() > 0) {
			exists = true;
		}
		if(null != session) {
			try {
				session.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return exists;
	}
	
	/*public Page findPage(RolesResourcesQuery query) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from RolesResources t where 1=1 "
			  	+ "/~ and t.resourceId = {resourceId} ~/"
			  	+ "/~ and t.resourceName = {resourceName} ~/"
			  	+ "/~ and t.roleId = {roleId} ~/"
			  	+ "/~ and t.roleName = {roleName} ~/"
				+ "/~ order by [sortColumns] ~/";

        //生成sql2的原因是为了不喜欢使用xsqlbuilder的同学，请修改生成器模板，删除本段的生成
        StringBuilder sql2 = new StringBuilder("select t from RolesResources t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql2.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getResourceId())) {
            sql2.append(" and  t.resourceId = :resourceId ");
        }
        if(isNotEmpty(query.getResourceName())) {
            sql2.append(" and  t.resourceName = :resourceName ");
        }
        if(isNotEmpty(query.getRoleId())) {
            sql2.append(" and  t.roleId = :roleId ");
        }
        if(isNotEmpty(query.getRoleName())) {
            sql2.append(" and  t.roleName = :roleName ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql2.append(" order by :sortColumns ");
        }	
        
		return pageQuery(sql,query);
	}*/
	

}
