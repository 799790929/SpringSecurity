

package com.byron.ss.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.byron.ss.common.base.BaseManager;
import com.byron.ss.common.base.EntityDao;
import com.byron.ss.dao.GroupsDao;
import com.byron.ss.model.Groups;
import com.byron.ss.model.Roles;
import com.byron.ss.model.Users;


/**
 * @author byron
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class GroupsManager extends BaseManager<Groups,java.lang.String>{

	private GroupsDao groupsDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setGroupsDao(GroupsDao dao) {
		this.groupsDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.groupsDao;
	}
	
	
	private GroupsRolesManager groupsRolesManager;
	
	
	
	public void setGroupsRolesManager(GroupsRolesManager groupsRolesManager) {
		this.groupsRolesManager = groupsRolesManager;
	}
	/*@Transactional(readOnly=true)
	public Page findPage(GroupsQuery query) {
		return groupsDao.findPage(query);
	}*/
	
	public long getRowsNotInRoleId(String roleId) {
		return groupsDao.getRowsNotInRoleId(roleId);
	}
	
	public List<Groups> queryByPageNotInRoleId(int start, int pageSize, String roleId) {
		return groupsDao.queryByPageNotInRoleId(start, pageSize, roleId);
	}
	
	/**
	 * 根据用户id删除相应的用户及用户组关系
	 * <p>Description: </p>
	 * @param id
	 * @throws Exception 
	 */
	public void doDeleteGroup(String id) throws Exception {
		this.groupsDao.doDeleteGroup(id);
		/*Session session = this.groupsDao.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			Groups group = this.getById(id);
			if(null != group) {
				List<GroupsRoles> list = this.groupsRolesManager.getEntityDao().findAllBy("groupId", group.getId());
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
		}*/
	}
	
	public List<Groups> getGroupsNotInRoleId(String roleId) {
		List<Groups> list = groupsDao.getGroupsNotInRoleId(roleId);
		
		return list;
	}
	
	public List<Groups> getGroupsByName(String name) {
		List<Groups> list = this.groupsDao.getGroupsByName(name);
		
		return list;
	}
	
	public List<Users> getUsersByGroupId(Groups group) {
		List<Users>  list = groupsDao.getUsersByGroupId(group);
		return list;
	}
	
	public List<Groups> getGroupsByRoleId(Roles role) {
		List<Groups>  list = groupsDao.getGroupsByRoleId(role);
		return list;
	}
}
