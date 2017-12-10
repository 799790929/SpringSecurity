/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.byron.ss.service;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.byron.ss.common.base.BaseManager;
import com.byron.ss.common.base.EntityDao;
import com.byron.ss.dao.ResourcesDao;
import com.byron.ss.model.Resources;
import com.byron.ss.model.RolesResources;


/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class ResourcesManager extends BaseManager<Resources,java.lang.String>{

	private ResourcesDao resourcesDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setResourcesDao(ResourcesDao dao) {
		this.resourcesDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.resourcesDao;
	}
	
	private RolesResourcesManager rolesResourcesManager;
	public void setRolesResourcesManager(RolesResourcesManager rolesResourcesManager) {
		this.rolesResourcesManager = rolesResourcesManager;
	}
	
	/*@Transactional(readOnly=true)
	public Page findPage(ResourcesQuery query) {
		return resourcesDao.findPage(query);
	}*/
	
	/**
	 * 根据资源id删除相应的角色资源关系
	 * @param id
	 * @throws Exception
	 */
	public void doDeleteResource(String id) throws Exception {
		Session session = this.resourcesDao.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			Resources resource = this.getById(id);
			if(null != resource) {
				List<RolesResources> list = this.rolesResourcesManager.getEntityDao().findAllBy("resourceId", id);
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
}
