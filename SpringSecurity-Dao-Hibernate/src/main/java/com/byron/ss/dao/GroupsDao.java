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

@Repository
public class GroupsDao extends BaseHibernateDao<Groups,java.lang.String>{
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	private GroupsRolesDao groupsRolesDao;
	
	private RolesDao rolesDao;

	public Class getEntityClass() {
		return Groups.class;
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

	public void setGroupsRolesDao(GroupsRolesDao groupsRolesDao) {
		this.groupsRolesDao = groupsRolesDao;
	}

	public void setRolesDao(RolesDao rolesDao) {
		this.rolesDao = rolesDao;
	}

	
}
