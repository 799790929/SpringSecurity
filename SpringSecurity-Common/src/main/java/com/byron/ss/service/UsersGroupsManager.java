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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.byron.ss.common.base.BaseManager;
import com.byron.ss.common.base.EntityDao;
import com.byron.ss.dao.UsersGroupsDao;
import com.byron.ss.model.Groups;
import com.byron.ss.model.Users;
import com.byron.ss.model.UsersGroups;


/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class UsersGroupsManager extends BaseManager<UsersGroups,java.lang.String>{
	protected Logger logger = Logger.getLogger(getClass());

	private UsersGroupsDao usersGroupsDao;
	
	private GroupsManager groupsManager;
	private UsersManager usersManager;
	
	
	
	public void setGroupsManager(GroupsManager groupsManager) {
		this.groupsManager = groupsManager;
	}
	public void setUsersManager(UsersManager usersManager) {
		this.usersManager = usersManager;
	}
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setUsersGroupsDao(UsersGroupsDao dao) {
		this.usersGroupsDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.usersGroupsDao;
	}
	
	/*@Transactional(readOnly=true)
	public Page findPage(UsersGroupsQuery query) {
		return usersGroupsDao.findPage(query);
	}*/
	

	
	public void doDeleteByUsers(String userid) {
		List<UsersGroups> usersGroups = this.getEntityDao().findBy("userId", userid, "");
		if(null != usersGroups && usersGroups.size() > 0) {
			for(UsersGroups po : usersGroups) {
				try {
					this.removeById(po.getId());
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void doDeleteByGroups(String groupid) {
		List<UsersGroups> usersGroups = this.getEntityDao().findBy("groupId", groupid, "");
		if(null != usersGroups && usersGroups.size() > 0) {
			for(UsersGroups po : usersGroups) {
				try {
					this.removeById(po.getId());
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public List<UsersGroups> queryByModel(UsersGroups ug) {
		return usersGroupsDao.queryByModel(ug);
	}
	
	//static final String u_pk_user = "ID_USER";
	//static final String g_pk_group = "ID_GROUP";
	//static final String ug_group_id = "GRP_ID";
	public boolean hasUserByGroup(Groups group) throws Exception {
		return this.usersGroupsDao.hasUserByGroup(group);
		/*boolean exists = false;
		Session session = this.usersGroupsDao.getSessionFactory().openSession();
		
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT {u.*} ");
		hql.append("FROM " + HibernateToolsUtil.getTableName(Users.class) + " u ");
		hql.append("," + HibernateToolsUtil.getTableName(UsersGroups.class) + " ug ");
		hql.append("," + HibernateToolsUtil.getTableName(Groups.class) + " g ");
		hql.append(" where 1=1 and u."+ Users.u_pk_user +"=ug.user_id and g."+ Groups.g_pk_group +"=ug."+ UsersGroups.ug_group_id +" ");
		if(null == group) {
			throw new Exception("群组不能为空!");
		}
		hql.append(" and g."+ Groups.g_pk_group +"='" + group.getId() + "' ");
		logger.info("\nsql->[" + hql.toString() + "]");
		SQLQuery query = session.createSQLQuery(hql.toString());
		query.addEntity("u", Users.class);
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
		
		return exists;*/
	}
	
	
	public List<Users> findByPage(HttpServletRequest request) {
		String groupid = request.getParameter("groupid");
		Groups group = null;
		if(null != groupid) {
			group = groupsManager.getById(groupid);
		}
		/*List<Users> users = null;*/
		List<Groups> groups = groupsManager.findAll();
		if(null != groups && groups.size() > 0) {
			if(null == group) {
				group = groups.get(0);
			}
			/*String hql = "from com.byron.ss.model.Users where PK_USER not in (select userId from com.byron.ss.model.UsersGroups where groupId='" + group.getId() + "')";
			users = usersManager.getEntityDao().executeFind(hql, null);*/
		}
		
		//获取查询条件
		/*String sqlWhere = " where 1=1 ";
		sqlWhere += " and "+ Users.u_pk_user +" not in (select userId from com.byron.ss.model.UsersGroups where groupId='" + group.getId() + "') ";
		*/
		int start = 0;
		long requestPage = 1;
		int pageSize = 20;
		// long rows = this.usersManager.getEntityDao().getRows(sqlWhere);
		long rows = this.usersManager.getRowsNotInGroupId(group.getId());
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
		request.setAttribute("groups", groups);
		request.setAttribute("group", group);
		
		request.setAttribute("requestPage", requestPage);
		request.setAttribute("pagesCount", pagesCount);
		// List<Users> list = this.usersManager.getEntityDao().queryByPage(start, pageSize, sqlWhere);
		List<Users> list = this.usersManager.queryByPageNotInGroupId(start, pageSize, group.getId());
		return list;
	}
}
