/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.byron.ss.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

import com.byron.ss.common.base.BaseHibernateDao;
import com.byron.ss.common.util.HibernateToolsUtil;
import com.byron.ss.model.Groups;
import com.byron.ss.model.GroupsRoles;
import com.byron.ss.model.Roles;

@Repository
public class GroupsRolesDao extends BaseHibernateDao<GroupsRoles,java.lang.String>{

	public Class getEntityClass() {
		return GroupsRoles.class;
	}
	
	public boolean hasGroupByRole(Roles role) throws Exception {
		boolean exists = false;
		Session session = this.getSessionFactory().openSession();
		
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
	
	public List<GroupsRoles> queryByModel(GroupsRoles gr) {
		String[] fieldNames = {"roleId", "groupId"};
		Object[] fieldValues = {gr.getRoleId(), gr.getGroupId()};
		List<GroupsRoles> groupsRoles = findBy(fieldNames, fieldValues, "");
		return groupsRoles;
	}
	
	/*public Page findPage(GroupsRolesQuery query) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from GroupsRoles t where 1=1 "
			  	+ "/~ and t.groupId = {groupId} ~/"
			  	+ "/~ and t.groupName = {groupName} ~/"
			  	+ "/~ and t.roleId = {roleId} ~/"
			  	+ "/~ and t.roleName = {roleName} ~/"
				+ "/~ order by [sortColumns] ~/";

        //生成sql2的原因是为了不喜欢使用xsqlbuilder的同学，请修改生成器模板，删除本段的生成
        StringBuilder sql2 = new StringBuilder("select t from GroupsRoles t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql2.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getGroupId())) {
            sql2.append(" and  t.groupId = :groupId ");
        }
        if(isNotEmpty(query.getGroupName())) {
            sql2.append(" and  t.groupName = :groupName ");
        }
        if(isNotEmpty(query.getRoleId())) {
            sql2.append(" and  t.roleId = :roleId ");
        }
        if(isNotEmpty(query.getRoleName())) {
            sql2.append(" and  t.roleName = :roleName ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql2.append(" order by :sortColumns ");
        }	
        
		return pageQuery(sql,query);
	}*/
	

}
