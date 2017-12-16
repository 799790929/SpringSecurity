/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.byron.ss.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.byron.ss.common.base.BaseManager;
import com.byron.ss.common.base.EntityDao;
import com.byron.ss.dao.GroupsDao;
import com.byron.ss.model.Groups;


/**
 * @author badqiu email:badqiu(a)gmail.com
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
}
