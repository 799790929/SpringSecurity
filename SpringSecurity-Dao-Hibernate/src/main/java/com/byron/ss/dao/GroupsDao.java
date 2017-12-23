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
import com.byron.ss.model.Groups;
import com.byron.ss.model.GroupsRoles;
import com.byron.ss.model.Roles;
import com.byron.ss.model.Users;

@Repository
public class GroupsDao extends BaseHibernateDao<Groups,java.lang.String>{
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	private GroupsRolesDao groupsRolesDao;
	
	private RolesDao rolesDao;
	
	private UsersDao usersDao;

	public Class getEntityClass() {
		return Groups.class;
	}
	
	public long getRowsNotInRoleId(String roleId) {
		String sqlWhere = " where 1=1 ";
		//sqlWhere += " and PK_GROUP not in (select groupId from com.byron.ss.model.UsersGroups where groupId='" + group.getId() + "') ";
		sqlWhere += " and "+ Groups.g_pk_group +" not in (select groupId from com.byron.ss.model.GroupsRoles where roleId='" + roleId + "') ";
		long rows = getRows(sqlWhere);
		return rows;
	}
	
	public List<Groups> queryByPageNotInRoleId(int start, int pageSize, String roleId) {
		String sqlWhere = " where 1=1 ";
		//sqlWhere += " and PK_GROUP not in (select groupId from com.byron.ss.model.UsersGroups where groupId='" + group.getId() + "') ";
		sqlWhere += " and "+ Groups.g_pk_group +" not in (select groupId from com.byron.ss.model.GroupsRoles where roleId='" + roleId + "') ";
		return queryByPage(start, pageSize, sqlWhere);
	}
	
	public void doDeleteGroup(String id) throws Exception {
		Session session = this.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			Groups group = this.getById(id);
			if(null != group) {
				List<GroupsRoles> list = this.groupsRolesDao.findAllBy("groupId", group.getId());
				if(null != list) {
					for(GroupsRoles gr : list) {
						session.delete(gr);
					}
				}
			}
			session.delete(group);
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
	
	public List<Roles> getRolesByGroupId(Groups group) {
		String hql1 = "from com.byron.ss.model.Roles where id in (select roleId from com.byron.ss.model.GroupsRoles where groupId='" + group.getId() + "')";
		List<Roles> roles = rolesDao.executeFind(hql1, null);
		
		return roles;
	}
	
	public List<Users> getUsersByGroupId(Groups group) {
		String hql1 = "from com.byron.ss.model.Users where id in (select userId from com.byron.ss.model.UsersGroups where groupId='" + group.getId() + "')";
		List<Users> users = usersDao.executeFind(hql1, null);
		
		return users;
	}
	
	public List<Groups> getGroupsByRoleId(Roles role) {
		String hql = "from com.byron.ss.model.Groups where id in (select groupId from com.byron.ss.model.GroupsRoles where roleId='" + role.getId() + "')";
		List<Groups> groups = this.executeFind(hql, null);
		
		return groups;
	}
	
	public List<Groups> getGroupsByName(String name) {
		List<Groups> list = findAllBy("name", name);
		
		return list;
	}
	
	public List<Groups> getGroupsNotInRoleId(String roleId) {
		String hql = "from com.byron.ss.model.Groups where not id in (select groupId from com.byron.ss.model.GroupsRoles where roleId='" + roleId + "')";
		return executeFind(hql, null);
	}
	

	public void setGroupsRolesDao(GroupsRolesDao groupsRolesDao) {
		this.groupsRolesDao = groupsRolesDao;
	}

	public void setRolesDao(RolesDao rolesDao) {
		this.rolesDao = rolesDao;
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	
}
