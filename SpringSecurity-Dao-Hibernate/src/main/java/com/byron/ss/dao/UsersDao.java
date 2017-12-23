
package com.byron.ss.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

import com.byron.ss.common.base.BaseHibernateDao;
import com.byron.ss.common.util.HibernateToolsUtil;
import com.byron.ss.model.Groups;
import com.byron.ss.model.GroupsRoles;
import com.byron.ss.model.Resources;
import com.byron.ss.model.Roles;
import com.byron.ss.model.RolesResources;
import com.byron.ss.model.Users;
import com.byron.ss.model.UsersGroups;

@Repository
public class UsersDao extends BaseHibernateDao<Users,java.lang.String>{
	private GroupsDao groupsDao;
	
	public Class getEntityClass() {
		return Users.class;
	}
	
	public long getRowsNotInGroupId(String groupId) {
		String sqlWhere = " where 1=1 ";
		sqlWhere += " and "+ Users.u_pk_user +" not in (select userId from com.byron.ss.model.UsersGroups where groupId='" + groupId + "') ";
		long rows = getRows(sqlWhere);
		return rows;
	}
	
	public List<Users> queryByPageNotInGroupId(int start, int pageSize, String groupId) {
		String sqlWhere = " where 1=1 ";
		sqlWhere += " and "+ Users.u_pk_user +" not in (select userId from com.byron.ss.model.UsersGroups where groupId='" + groupId + "') ";
		return queryByPage(start, pageSize, sqlWhere);
	}
	
	public long getRowsBySql(String query) {
		Session session = this.getSessionFactory().openSession();
		String queryString = "select count(*) from " + getEntityClass().getName();
		if (query != null) {
			queryString += " " + query;
		}
		Query q = session.createQuery(queryString);
		
		return (Long) q.list().get(0);
	}
	
	public List<Users> getUsersByGroupId(String groupId) {
		String hql = "from com.byron.ss.model.Users where id in (select userId from com.byron.ss.model.UsersGroups where groupId='" + groupId + "')";
		List<Users> users = executeFind(hql, null);
		return users;
	}
	
	public List<Users> getUsersNotInGroupId(String groupId) {
		String hql = "from com.byron.ss.model.Users where id not in (select userId from com.byron.ss.model.UsersGroups where groupId='" + groupId + "')";
		List<Users> users = executeFind(hql, null);
		return users;
	}

	public List<Groups> getGroupsByUser(Users user) throws Exception {
		Session session = this.getSessionFactory().openSession();
		
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT {g.*} ");
		hql.append("FROM " + HibernateToolsUtil.getTableName(Users.class) + " u ");
		hql.append("," + HibernateToolsUtil.getTableName(UsersGroups.class) + " ug ");
		hql.append("," + HibernateToolsUtil.getTableName(Groups.class) + " g ");
		hql.append(" where 1=1 and u."+ Users.u_pk_user +"=ug.user_id and g."+ Groups.g_pk_group +"=ug."+ UsersGroups.ug_group_id +" ");
		if(null == user) {
			throw new Exception("用户不能为空!");
		}
		hql.append(" and u." + Users.u_pk_user + "='" + user.getId() + "' ");
		logger.info("\nsql->[" + hql.toString() + "]");
		SQLQuery query = session.createSQLQuery(hql.toString());
		query.addEntity("g", Groups.class);
		List<Groups> listFinal = new ArrayList<Groups>();
		List<Object> list = query.list();
		if(null != list && list.size() > 0) {
			for(Object obj : list) {
				Groups g = (Groups) obj;
				listFinal.add(g);
			}
		}
		if(null != session) {
			try {
				session.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return listFinal;
	}
	
	public List<Groups> getGroupsByUserId(Users user) {
		String hql = "from com.byron.ss.model.Groups where id in (select groupId from com.byron.ss.model.UsersGroups where userId='" + user.getId() + "')";
		List<Groups> groups = groupsDao.executeFind(hql, null);
		
		return groups;
	}
	
	public List<Roles> getRolesByUser(Users user) {
		Session session = this.getSessionFactory().getCurrentSession();
		
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT {r.*} ");
		hql.append("FROM " + HibernateToolsUtil.getTableName(Users.class) + " u ");
		hql.append("," + HibernateToolsUtil.getTableName(UsersGroups.class) + " ug ");
		hql.append("," + HibernateToolsUtil.getTableName(Groups.class) + " g ");
		hql.append("," + HibernateToolsUtil.getTableName(GroupsRoles.class) + " gr ");
		hql.append("," + HibernateToolsUtil.getTableName(Roles.class) + " r ");
		hql.append(" where 1=1 and u."+ Users.u_pk_user +"=ug.user_id and g."+ Groups.g_pk_group +"=ug."+ UsersGroups.ug_group_id +" ");
		hql.append(" and g."+ Groups.g_pk_group +"=gr."+ GroupsRoles.gr_group_id +" and r."+Roles.r_pk_role+"=gr.role_id ");
		if(null == user) {
			return null;
		}
		hql.append(" and u."+ Users.u_pk_user +"='" + user.getId() + "' ");
		logger.info("\nsql->[" + hql.toString() + "]");
		SQLQuery query = session.createSQLQuery(hql.toString());
		query.addEntity("r", Roles.class);
		List<Roles> listFinal = new ArrayList<Roles>();
		List<Object> list = query.list();
		if(null != list && list.size() > 0) {
			for(Object obj : list) {
				Roles r = (Roles) obj;
				listFinal.add(r);
			}
		}
		/*if(null != session) {
			try {
				session.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}*/
		return listFinal;
	}
	
	public List<Resources> getResourcesByUser(Users user) throws Exception {
		Session session = this.getSessionFactory().openSession();
		
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT {rs.*} ");
		hql.append("FROM " + HibernateToolsUtil.getTableName(Users.class) + " u ");
		hql.append("," + HibernateToolsUtil.getTableName(UsersGroups.class) + " ug ");
		hql.append("," + HibernateToolsUtil.getTableName(Groups.class) + " g ");
		hql.append("," + HibernateToolsUtil.getTableName(GroupsRoles.class) + " gr ");
		hql.append("," + HibernateToolsUtil.getTableName(Roles.class) + " r ");
		hql.append("," + HibernateToolsUtil.getTableName(RolesResources.class) + " rrs ");
		hql.append("," + HibernateToolsUtil.getTableName(Resources.class) + " rs ");
		hql.append(" where 1=1 and u."+Users.u_pk_user+"=ug.user_id and g."+ Groups.g_pk_group +"=ug."+UsersGroups.ug_group_id+" ");
		hql.append(" and g."+Groups.g_pk_group+"=gr."+GroupsRoles.gr_group_id+" and r."+ Roles.r_pk_role +"=gr.role_id ");
		hql.append(" and r."+ Roles.r_pk_role +"=rrs.role_id and rs."+ Resources.rs_pk_resource +"=rrs.resource_id ");
		if(null == user) {
			throw new Exception("用户不能为空!");
		}
		hql.append(" and u."+Users.u_pk_user+"='" + user.getId() + "' ");
		logger.info("\nsql->[" + hql.toString() + "]");
		SQLQuery query = session.createSQLQuery(hql.toString());
		query.addEntity("rs", Resources.class);
		List<Resources> listFinal = new ArrayList<Resources>();
		List<Object> list = query.list();
		if(null != list && list.size() > 0) {
			for(Object obj : list) {
				Resources rs = (Resources) obj;
				listFinal.add(rs);
			}
		}
		if(null != session) {
			try {
				session.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return listFinal;
	}
	
	public Users findByName(String name) {
		List<Users> objs = this.findBy("username", name, "");
//		List<Users> objs = this.findByCriteria(Restrictions.eq("account", name));
		return (objs.size() == 0 ? null : objs.get(0)); 
	}
	
	public List<Users> queryUsersByName(String name) {
		List<Users> objs = this.findBy("username", name, "");
		return objs;
	}

	public void setGroupsDao(GroupsDao groupsDao) {
		this.groupsDao = groupsDao;
	}
	
	
}
