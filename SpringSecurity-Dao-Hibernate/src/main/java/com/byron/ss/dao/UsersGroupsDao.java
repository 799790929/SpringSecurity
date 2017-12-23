

package com.byron.ss.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

import com.byron.ss.common.base.BaseHibernateDao;
import com.byron.ss.common.util.HibernateToolsUtil;
import com.byron.ss.model.Groups;
import com.byron.ss.model.Users;
import com.byron.ss.model.UsersGroups;

@Repository
public class UsersGroupsDao extends BaseHibernateDao<UsersGroups,java.lang.String>{

	public Class getEntityClass() {
		return UsersGroups.class;
	}
	
	public List<UsersGroups> getUsersGroupsByUserId(String userId) {
		List<UsersGroups> list = this.findAllBy("userId", userId);
		return list;
	}
	
	public boolean hasUserByGroup(Groups group) throws Exception {
		boolean exists = false;
		Session session = this.getSessionFactory().openSession();
		
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
		
		return exists;
	}
	
	public List<UsersGroups> queryByModel(UsersGroups ug) {
		String[] fieldNames = {"userId", "groupId"};
		Object[] fieldValues = {ug.getUserId(), ug.getGroupId()};
		List<UsersGroups> list = findBy(fieldNames, fieldValues, "");
		
		return list;
	}
	
	/*public Page findPage(UsersGroupsQuery query) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from UsersGroups t where 1=1 "
			  	+ "/~ and t.groupId = {groupId} ~/"
			  	+ "/~ and t.groupName = {groupName} ~/"
			  	+ "/~ and t.userId = {userId} ~/"
			  	+ "/~ and t.userName = {userName} ~/"
				+ "/~ order by [sortColumns] ~/";

        //生成sql2的原因是为了不喜欢使用xsqlbuilder的同学，请修改生成器模板，删除本段的生成
        StringBuilder sql2 = new StringBuilder("select t from UsersGroups t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql2.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getGroupId())) {
            sql2.append(" and  t.groupId = :groupId ");
        }
        if(isNotEmpty(query.getGroupName())) {
            sql2.append(" and  t.groupName = :groupName ");
        }
        if(isNotEmpty(query.getUserId())) {
            sql2.append(" and  t.userId = :userId ");
        }
        if(isNotEmpty(query.getUserName())) {
            sql2.append(" and  t.userName = :userName ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql2.append(" order by :sortColumns ");
        }	
        
		return pageQuery(sql,query);
	}
	*/

}
