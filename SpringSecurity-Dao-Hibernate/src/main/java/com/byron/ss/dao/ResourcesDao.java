/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.byron.ss.dao;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

import com.byron.ss.common.base.BaseHibernateDao;
import com.byron.ss.model.Resources;
import com.byron.ss.model.Roles;
import com.byron.ss.model.RolesResources;
import com.byron.ss.model.Users;

@Repository
public class ResourcesDao extends BaseHibernateDao<Resources,java.lang.String>{
	private RolesResourcesDao rolesResourcesDao;
	
	
	public Class getEntityClass() {
		return Resources.class;
	}
	
	public long getRowsNotInRoleId(String roleId) {
		String sqlWhere = " where 1=1 ";
		//sqlWhere += " and PK_GROUP not in (select groupId from com.byron.ss.model.UsersGroups where groupId='" + group.getId() + "') ";
		sqlWhere += " and "+ Resources.rs_pk_resource +" not in (select resourceId from com.byron.ss.model.RolesResources where roleId='" + roleId + "')";
		return getRows(sqlWhere);
	}
	
	public List<Resources> queryByPageNotInRoleId(int start, int pageSize, String roleId) {
		String sqlWhere = " where 1=1 ";
		//sqlWhere += " and PK_GROUP not in (select groupId from com.byron.ss.model.UsersGroups where groupId='" + group.getId() + "') ";
		sqlWhere += " and "+ Resources.rs_pk_resource +" not in (select resourceId from com.byron.ss.model.RolesResources where roleId='" + roleId + "')";
		return queryByPage(start, pageSize, sqlWhere);
	}
	
	public void doDeleteResource(String id) throws Exception {
		Session session = this.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			Resources resource = this.getById(id);
			if(null != resource) {
				List<RolesResources> list = this.rolesResourcesDao.findAllBy("resourceId", id);
				if(null != list) {
					for(RolesResources rrs : list) {
						session.delete(rrs);
					}
				}
			}
			session.delete(resource);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			throw new Exception("删除失败!");
		} finally {
			if(null != session) {
				try {
					session.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public List<Resources> getResourcesNotInRowId(String roleId) {
		String hql = "from com.byron.ss.model.Resources where id not in (select resourceId from com.byron.ss.model.RolesResources where roleId='" + roleId + "')";
		List<Resources> resources = executeFind(hql, null);
		return resources;
	}
	
	final static String rss_role_id = "";//资源表
	final static String rss_pk_resource = "ID_RESOURCE";//资源表id
	final static String r_pk_role = "ID_ROLE";//角色表id
	final static String r_role_name = "ROLE_NAME";//角色表name
	public List<String> getResourcesByRole(Roles role) {
		Session session = this.getSessionFactory().openSession();
		String sql = "select rs.url from ts_role r,tr_role_resource rss, ts_resource rs " +
				" where rss.role_id=r."+ r_pk_role +" and rss.resource_id=rs."+ rss_pk_resource +" and " +
				" r."+ r_role_name +"='" + role.getName() + "'";
		List<String> list = session.createSQLQuery(sql).list();
		try {
			if(null != session) {
				session.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Resources> getResourcesByName(String name) {
		return findAllBy("name", name);
	}
	
	public List<Resources> getResourcesByRoleId(Roles role) {
		String hql1 = "from com.byron.ss.model.Resources where id in (select resourceId from com.byron.ss.model.RolesResources where roleId='" + role.getId() + "')";
		List<Resources> list = this.executeFind(hql1, null);
		
		return list;
	}
	
	public void setRolesResourcesDao(RolesResourcesDao rolesResourcesDao) {
		this.rolesResourcesDao = rolesResourcesDao;
	}
	
	
}
