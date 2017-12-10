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

import com.byron.ss.common.base.BaseManager;
import com.byron.ss.common.base.EntityDao;
import com.byron.ss.common.util.HibernateToolsUtil;
import com.byron.ss.dao.GroupsRolesDao;
import com.byron.ss.model.Groups;
import com.byron.ss.model.GroupsRoles;
import com.byron.ss.model.Roles;


/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class GroupsRolesManager extends BaseManager<GroupsRoles,java.lang.String>{
	protected Logger logger = Logger.getLogger(getClass());

	private GroupsRolesDao groupsRolesDao;
	private RolesManager rolesManager;
	private GroupsManager groupsManager;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setGroupsRolesDao(GroupsRolesDao dao) {
		this.groupsRolesDao = dao;
	}
	
	public void setRolesManager(RolesManager rolesManager) {
		this.rolesManager = rolesManager;
	}

	public void setGroupsManager(GroupsManager groupsManager) {
		this.groupsManager = groupsManager;
	}

	public EntityDao getEntityDao() {
		return this.groupsRolesDao;
	}
	
	/*@Transactional(readOnly=true)
	public Page findPage(GroupsRolesQuery query) {
		return groupsRolesDao.findPage(query);
	}*/

	public void doDeleteByGroups(String groupid) {
		List<GroupsRoles> groupsRoles = this.getEntityDao().findBy("groupId", groupid, "");
		if(null != groupsRoles && groupsRoles.size() > 0) {
			for(GroupsRoles po : groupsRoles) {
				try {
					this.removeById(po.getId());
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void doDeleteByRoles(String roleid) {
		List<GroupsRoles> groupsRoles = this.getEntityDao().findBy("roleId", roleid, "");
		if(null != groupsRoles && groupsRoles.size() > 0) {
			for(GroupsRoles po : groupsRoles) {
				try {
					this.removeById(po.getId());
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//final static String g_pk_group = "ID_GROUP";//群组表id
	//final static String gr_group_id = "GRP_ID";//群组角色   群组id
	//final static String gr_role_id = "ROLE_ID";//群组角色   群组id
	//final static String r_pk_role = "ID_ROLE";//角色   id
	
	public boolean hasGroupByRole(Roles role) throws Exception {
		boolean exists = false;
		Session session = this.groupsRolesDao.getSessionFactory().openSession();
		
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT {g.*} ");
		hql.append("FROM " + HibernateToolsUtil.getTableName(Groups.class) + " g ");
		hql.append("," + HibernateToolsUtil.getTableName(GroupsRoles.class) + " gr ");
		hql.append("," + HibernateToolsUtil.getTableName(Roles.class) + " r ");
		hql.append(" where 1=1 and g."+ Groups.g_pk_group +"=gr."+ GroupsRoles.gr_group_id +" and r."+ Roles.r_pk_role +"=gr."+ GroupsRoles.gr_role_id +" ");
		if(null == role) {
			throw new Exception("角色不能为空!");
		}
		hql.append(" and r."+ Roles.r_pk_role +"='" + role.getId() + "' ");
		logger.info("\nsql->[" + hql.toString() + "]");
		SQLQuery query = session.createSQLQuery(hql.toString());
		query.addEntity("g", Groups.class);
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
	
	public List<Groups> findByPage(HttpServletRequest request) {
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
		sqlWhere += " and "+ Groups.g_pk_group +" not in (select groupId from com.byron.ss.model.GroupsRoles where roleId='" + role.getId() + "') ";
		int start = 0;
		long requestPage = 1;
		int pageSize = 20;
		long rows = this.groupsManager.getEntityDao().getRows(sqlWhere);
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
		List<Groups> list = this.groupsManager.getEntityDao().queryByPage(start, pageSize, sqlWhere);
		
		return list;
	}
}
