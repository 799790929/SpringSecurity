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
import com.byron.ss.dao.RolesDao;
import com.byron.ss.model.Roles;


/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class RolesManager extends BaseManager<Roles,java.lang.String>{

	private RolesDao rolesDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setRolesDao(RolesDao dao) {
		this.rolesDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.rolesDao;
	}
	
	/*@Transactional(readOnly=true)
	public Page findPage(RolesQuery query) {
		return rolesDao.findPage(query);
	}*/
	
}
