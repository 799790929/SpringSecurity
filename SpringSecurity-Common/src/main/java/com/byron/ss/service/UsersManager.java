/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.byron.ss.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.byron.ss.common.base.BaseManager;
import com.byron.ss.common.base.EntityDao;
import com.byron.ss.dao.GroupsDao;
import com.byron.ss.dao.UsersDao;
import com.byron.ss.dao.UsersGroupsDao;
import com.byron.ss.model.Groups;
import com.byron.ss.model.Resources;
import com.byron.ss.model.Roles;
import com.byron.ss.model.Users;
import com.byron.ss.model.UsersGroups;
import com.byron.ss.util.StringUtil;


/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class UsersManager extends BaseManager<Users,java.lang.String>{
	protected Logger logger = Logger.getLogger(getClass());

	private UsersDao usersDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setUsersDao(UsersDao dao) {
		this.usersDao = dao;
	}
	
	private GroupsDao groupsDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setGroupsDao(GroupsDao dao) {
		this.groupsDao = dao;
	}
	
	private UsersGroupsManager usersGroupsManager;
	
	private UsersGroupsDao usersGroupsDao;
	
	public void setUsersGroupsManager(UsersGroupsManager usersGroupsManager) {
		this.usersGroupsManager = usersGroupsManager;
	}
	
	

	public void setUsersGroupsDao(UsersGroupsDao usersGroupsDao) {
		this.usersGroupsDao = usersGroupsDao;
	}



	public EntityDao getEntityDao() {
		return this.usersDao;
	}
	
	/*@Transactional(readOnly=true)
	public Page findPage(UsersQuery query) {
		return usersDao.findPage(query);
	}*/
	
	
	
	
	public List<Users> findByPage(HttpServletRequest request) {
		String username = request.getParameter("username");
		
		//获取查询条件
		String sqlWhere = " where 1=1 ";
		String sqlWhere1 = " where 1=1 ";
		// String sql = " where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if(null != username) {
			sqlWhere += " and username like ? ";
			params.add("%" + StringUtil.nullStringTrim(username) + "%");
			
			
			sqlWhere1 += " and username like '%" + StringUtil.nullStringTrim(username) + "%' ";
//			sqlWhere += " and username like '%" + username + "%' ";
		}
		
		Object[] parameters = params.toArray();
		int start = 0;
		long requestPage = 1;
		int pageSize = 20;
		long rows = this.usersDao.getRowsBySql(sqlWhere1);
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
		String sqlOrder = " order by id ";
		
		start = (int)(requestPage - 1) * pageSize;
		request.setAttribute("requestPage", requestPage);
		request.setAttribute("pagesCount", pagesCount);
		List<Users> list = this.getEntityDao().queryByPage(start, pageSize, sqlWhere1 + sqlOrder);
		
		return list;
	}
	
	/**
	 * 根据用户id删除相应的用户及用户组关系
	 * <p>Description: </p>
	 * @param id
	 * @throws Exception 
	 */
	public void doDeleteUser(String id) throws Exception {
		
		
		/*Session session = this.usersDao.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();*/
		
		try {
			Users user = this.getById(id);
			if(null != user) {
				List<UsersGroups> list = this.usersGroupsDao.getUsersGroupsByUserId(id);
				
				if(null != list) {
					for(UsersGroups ug : list) {
						//session.delete(ug);
						this.usersGroupsManager.getEntityDao().deleteById(ug.getId());
					}
				}
			}
			
			this.usersDao.delete(user);
			/*session.delete(user);
			tx.commit();*/
		} catch(Exception e) {
			//tx.rollback();
			throw new Exception("删除失败!");
		} finally {
			/*if(null != session) {
				try {
					session.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}*/
		}
	}
	
	/**
	 * 查询用户所在组
	 * <p>Description: TODO</p>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<Groups> getGroupsByUser(Users user) throws Exception {
		return this.usersDao.getGroupsByUser(user);
		/*Session session = this.usersDao.getSessionFactory().openSession();
		
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
		return listFinal;*/
	}
	//static final String gr_group_id = "GRP_ID";
	//static final String r_pk_role = "ID_ROLE";
	public List<Roles> getRolesByUser(Users user) {
		return this.usersDao.getRolesByUser(user);
		/*Session session = this.usersDao.getSessionFactory().getCurrentSession();
		
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
		if(null != session) {
			try {
				session.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return listFinal;*/
	}
	
	public List<Resources> getResourcesByUser(Users user) throws Exception {
		return this.usersDao.getResourcesByUser(user);
		/*Session session = this.usersDao.getSessionFactory().openSession();
		
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
		return listFinal;*/
	}
	
	/**
	 * 
	 * <p>Description: 通过属性获取代码</p>
	 * @param propertyName 
	 * @return
	 */
	/*public List<Users> findUsersByProperty(String propertyName , Object value) {
		return this.usersDao.findAllBy(propertyName , value);k
	}*/
}
