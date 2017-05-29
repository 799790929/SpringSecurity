/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.byron.ss.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.org.rapid_framework.page.Page;

import com.byron.ss.common.base.BaseManager;
import com.byron.ss.common.base.EntityDao;
import com.byron.ss.common.util.HibernateToolsUtil;
import com.byron.ss.dao.RolesResourcesDao;
import com.byron.ss.model.Resources;
import com.byron.ss.model.Roles;
import com.byron.ss.model.RolesResources;
import com.byron.ss.vo.query.RolesResourcesQuery;


/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class RolesResourcesManager extends BaseManager<RolesResources,java.lang.String>{
	protected Logger logger = Logger.getLogger(getClass());

	private RolesResourcesDao rolesResourcesDao;
	private RolesManager rolesManager;
	private ResourcesManager resourcesManager;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setRolesResourcesDao(RolesResourcesDao dao) {
		this.rolesResourcesDao = dao;
	}
	
	public void setRolesManager(RolesManager rolesManager) {
		this.rolesManager = rolesManager;
	}

	public void setResourcesManager(ResourcesManager resourcesManager) {
		this.resourcesManager = resourcesManager;
	}

	public EntityDao getEntityDao() {
		return this.rolesResourcesDao;
	}
	
	@Transactional(readOnly=true)
	public Page findPage(RolesResourcesQuery query) {
		return rolesResourcesDao.findPage(query);
	}
	
	public void doDeleteByRoles(String roleid) {
		List<RolesResources> rolesResources = this.getEntityDao().findBy("roleId", roleid, "");
		if(null != rolesResources && rolesResources.size() > 0) {
			for(RolesResources po : rolesResources) {
				try {
					this.removeById(po.getId());
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void doDeleteByResources(String resourceid) {
		List<RolesResources> rolesResources = this.getEntityDao().findBy("resourceid", resourceid, "");
		if(null != rolesResources && rolesResources.size() > 0) {
			for(RolesResources po : rolesResources) {
				try {
					this.removeById(po.getId());
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//static final String rs_pk_resource = "ID_RESOURCE";//资源表id
	//static final String r_pk_role = "ID_ROLE";
	public boolean hasResourceByRole(Roles role) throws Exception {
		boolean exists = false;
		Session session = this.rolesResourcesDao.getSessionFactory().openSession();
		
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
	
	public List<Resources> findByPage(HttpServletRequest request) {
		String roleid = request.getParameter("roleid");
		Roles role = null;
		if(null != roleid) {
			role = rolesManager.getById(roleid);
		}
		/*List<Users> users = null;*/
		List<Roles> roles = rolesManager.findAll();
		if(null != roles && roles.size() > 0) {
			if(null == role) {
				role = roles.get(0);
			}
			/*String hql = "from com.byron.ss.model.Users where PK_USER not in (select userId from com.byron.ss.model.UsersGroups where groupId='" + group.getId() + "')";
			users = usersManager.getEntityDao().executeFind(hql, null);*/
		}
		
		//获取查询条件
		String sqlWhere = " where 1=1 ";
		//sqlWhere += " and PK_GROUP not in (select groupId from com.byron.ss.model.UsersGroups where groupId='" + group.getId() + "') ";
		sqlWhere += " and "+ Resources.rs_pk_resource +" not in (select resourceId from com.byron.ss.model.RolesResources where roleId='" + role.getId() + "')";
		int start = 0;
		long requestPage = 1;
		int pageSize = 20;
		long rows = this.resourcesManager.getEntityDao().getRows(sqlWhere);
		try {
			requestPage = Integer.parseInt(request.getParameter("requestPage"));
			if(requestPage < 1) {
				requestPage  = 1;
			}
		} catch(Exception e) {
		}
		long pagesCount = rows / pageSize;
		if(rows % pageSize != 0) {
			pagesCount++;
		}
		if(requestPage > pagesCount && pagesCount != 0) {
			requestPage = pagesCount;
		}
		
		start = (int)(requestPage - 1) * pageSize;
		
		/*request.setAttribute("users", users);*/
		request.setAttribute("roles", roles);
		request.setAttribute("role", role);
		
		request.setAttribute("requestPage", requestPage);
		request.setAttribute("pagesCount", pagesCount);
		List<Resources> list = this.resourcesManager.getEntityDao().queryByPage(start, pageSize, sqlWhere);
		
		return list;
	}
}
